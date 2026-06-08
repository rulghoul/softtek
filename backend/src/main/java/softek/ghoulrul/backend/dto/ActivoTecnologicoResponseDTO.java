package softek.ghoulrul.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softek.ghoulrul.backend.utils.excel.Celda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de un Activo Tecnológico")
public class ActivoTecnologicoResponseDTO {

    @Schema(description = "Identificador único técnico", example = "123e4567-e89b-12d3-a456-426614174000")
    private String identificadorTecnico;

    @Schema(description = "Folio de inventario", example = "LAP-2026-001")
    private String folioInventario;

    @Schema(description = "Número de serie", example = "SN-987654321")
    private String numeroDeSerie;

    @Schema(description = "Marca y modelo", example = "Dell Latitude 5420")
    private String marcaModelo;

    @Schema(description = "Estado actual", example = "Disponible, Asignado, En Mantenimiento, Baja")
    private String estado;

    @Schema(description = "Costo de adquisición", example = "15000.00")
    private BigDecimal costoAdquisicion;

    @Schema(description = "Fecha y hora de registro", example = "2023-10-25T14:30:00")
    private LocalDateTime fechaHora;

    @Schema(description = "Nombre de la categoría", example = "Laptops")
    private String categoria;

    public List<Celda> toCeldaList(){
        List<Celda> result = new ArrayList<>();
        result.add(new Celda(folioInventario, "normal", 1));
        result.add(new Celda(numeroDeSerie, "normal", 1));
        result.add(new Celda(marcaModelo, "normal", 1));
        result.add(new Celda(estado, "normal", 1));
        result.add(new Celda(costoAdquisicion,  "moneda", 1));
        result.add(new Celda(fechaHora,  "normal", 1));
        result.add(new Celda(categoria,  "normal", 1));
        return result;
    }
}