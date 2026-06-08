package softek.ghoulrul.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softek.ghoulrul.backend.dto.ActivoFiltroDTO;
import softek.ghoulrul.backend.dto.ActivoTecnologicoRequestDTO;
import softek.ghoulrul.backend.dto.ActivoTecnologicoResponseDTO;
import softek.ghoulrul.backend.entities.ActivoTecnologico;
import softek.ghoulrul.backend.exceptions.InventarioException;
import softek.ghoulrul.backend.service.ActivoTecnologicoService;

import java.util.UUID;

@RestController
@RequestMapping("activos")
@Tag(name = "Activos Tecnológicos", description = "API para la gestión (CRUD) de activos tecnológicos del inventario")
public class ActivoTecnologicoController {

    private ActivoTecnologicoService activoService;

    @Autowired
    public ActivoTecnologicoController(ActivoTecnologicoService service){
        this.activoService = service;
    }

    @Operation(summary = "Crear un nuevo activo tecnológico", description = "Registra un activo en el sistema. La categoría se resuelve internamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activo creado exitosamente", content = @Content(schema = @Schema(implementation = ActivoTecnologico.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o categoría no encontrada", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<ActivoTecnologicoResponseDTO> crearActivo(@Valid @RequestBody ActivoTecnologicoRequestDTO activo) {
        try {
            ActivoTecnologicoResponseDTO guardado = activoService.guardarActivo(activo);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new InventarioException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Obtener todos los activos con paginación", description = "Devuelve una página de activos. Parámetros de URL: ?page=0&size=10&sort=fechaHora,desc")
    @ApiResponse(responseCode = "200", description = "Lista paginada de activos")
    @GetMapping
    public ResponseEntity<Page<ActivoTecnologicoResponseDTO>> obtenerTodos(
            @Parameter(description = "Parámetros de paginación y ordenamiento")
            @PageableDefault(page = 0, size = 10, sort = "fechaHora") Pageable pageable) {

        Page<ActivoTecnologicoResponseDTO> pagina = activoService.getAll(pageable);
        if (pagina.isEmpty()) {
            throw new InventarioException(HttpStatus.NOT_FOUND, "La base de datos está vacía");
        }
        return ResponseEntity.ok(pagina);
    }

    @Operation(summary = "Buscar activos por nombre de categoría", description = "Busca activos filtrando por el nombre de la categoría (ej: 'Laptops'). Usa caché en el servicio para resolver la categoría.")
    @ApiResponse(responseCode = "200", description = "Lista paginada de activos de la categoría")
    @PostMapping("/busqueda")
    public ResponseEntity<Page<ActivoTecnologicoResponseDTO>> buscarPorCategoria(
            @RequestBody(required = false) ActivoFiltroDTO filtros,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        if (filtros == null) {
            filtros = ActivoFiltroDTO.builder().build();
        }

        Page<ActivoTecnologicoResponseDTO> resultados = activoService.filtrado(filtros, pageable);

        if (resultados.isEmpty()) {
            throw new InventarioException(HttpStatus.NOT_FOUND, "Sin resultados");
        }

        return ResponseEntity.ok(resultados);
    }



    @Operation(summary = "Actualizar un activo existente", description = "Actualiza los datos de un activo basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activo actualizado"),
            @ApiResponse(responseCode = "404", description = "Activo no encontrado para actualizar")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ActivoTecnologicoResponseDTO> actualizarActivo(
            @PathVariable UUID id,
            @Valid @RequestBody ActivoTecnologico activoActualizado) {

        return ResponseEntity.ok(activoService.actualizarActivo(id, activoActualizado));
    }


}
