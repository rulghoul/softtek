package softek.ghoulrul.backend.service;

import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softek.ghoulrul.backend.dto.ActivoFiltroDTO;
import softek.ghoulrul.backend.dto.ActivoTecnologicoRequestDTO;
import softek.ghoulrul.backend.dto.ActivoTecnologicoResponseDTO;
import softek.ghoulrul.backend.entities.ActivoTecnologico;
import softek.ghoulrul.backend.repository.ActivoTecnologicoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ActivoTecnologicoService {

    private ActivoTecnologicoRepository repository;

    @Autowired
    public ActivoTecnologicoService(ActivoTecnologicoRepository repository){
        this.repository = repository;
    }

    public Page<ActivoTecnologicoResponseDTO> getAll(Pageable pageable){
        var activos = repository.findAll(pageable);
        return activos.map(this::mapearRespuesta);
    }

    @Transactional(readOnly = true)
    public Page<ActivoTecnologicoResponseDTO> filtrado(ActivoFiltroDTO filtro, Pageable pageable){
        Specification<ActivoTecnologico> esfecificacion = this.conFiltros(filtro);
        var activos = repository.findAll(esfecificacion, pageable);
        return activos.map(this::mapearRespuesta);
    }


    private Specification<ActivoTecnologico> conFiltros(ActivoFiltroDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. Filtro por Número de Serie (LIKEIgnoreCase)
            if (!Objects.isNull(filtro.getNumeroDeSerie()) && !filtro.getNumeroDeSerie().isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("numeroDeSerie")),
                        "%" + filtro.getNumeroDeSerie().toLowerCase() + "%"));
            }

            // 2. Filtro por Marca/Modelo (LIKEIgnoreCase)
            if (!Objects.isNull(filtro.getMarcaModelo()) && !filtro.getMarcaModelo().isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("marcaModelo")),
                        "%" + filtro.getMarcaModelo().toLowerCase() + "%"));
            }

            // 3. Filtro por Estado (LIKEIgnoreCase)
            if (!Objects.isNull(filtro.getEstado()) && !filtro.getEstado().isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("estado")),
                        "%" + filtro.getEstado().toLowerCase() + "%"));
            }

            // 4. Filtro por Rango de Costo (BETWEEN)
            if (!Objects.isNull(filtro.getCostoMin()) && !Objects.isNull(filtro.getCostoMax())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("costoAdquisicion"), filtro.getCostoMin()));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("costoAdquisicion"), filtro.getCostoMax()));
            }

            // 5. Filtro por Categoría (JOIN implícito con la entidad Categoria)
            if (!Objects.isNull(filtro.getNombreCategoria()) && !filtro.getNombreCategoria().isBlank()) {
                // Asumiendo que tu entidad Categoria tiene un atributo llamado "nombre"
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("categoria").get("nombre")),
                        "%" + filtro.getNombreCategoria().toLowerCase() + "%"));
            }

            // Unir todas las condiciones con AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public ActivoTecnologicoResponseDTO guardarActivo(@Valid ActivoTecnologicoRequestDTO activo) {
        return new ActivoTecnologicoResponseDTO();
    }

    public ActivoTecnologicoResponseDTO actualizarActivo(UUID id, @Valid ActivoTecnologico activoActualizado) {
        return new ActivoTecnologicoResponseDTO();
    }

    private  ActivoTecnologicoResponseDTO mapearRespuesta(ActivoTecnologico entity){
        return ActivoTecnologicoResponseDTO.builder()
                .identificadorTecnico(entity.getIdentificadorTecnico().toString())
                .folioInventario(entity.getFolioInventario())
                .numeroDeSerie(entity.getNumeroDeSerie())
                .marcaModelo(entity.getMarcaModelo())
                .estado(entity.getEstado())
                .costoAdquisicion(entity.getCostoAdquisicion())
                .fechaHora(entity.getFechaHora())
                .categoria(entity.getCategoria() != null ? entity.getCategoria().getNombre() : "Sin categoría")
                .build();
        }

}
