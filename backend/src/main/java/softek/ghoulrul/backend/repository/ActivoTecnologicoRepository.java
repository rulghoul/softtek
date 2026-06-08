package softek.ghoulrul.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import softek.ghoulrul.backend.entities.ActivoTecnologico;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface ActivoTecnologicoRepository  extends JpaRepository<ActivoTecnologico, UUID>,
        JpaSpecificationExecutor<ActivoTecnologico> {



    Page<ActivoTecnologico> findByNumeroDeSerieLikeIgnoreCase(String numeroDeSerie, Pageable pageable);

    Page<ActivoTecnologico> findByMarcaModeloLikeIgnoreCase(String marcaModelo, Pageable pageable);

    Page<ActivoTecnologico> findByEstadoLikeIgnoreCase(String estado, Pageable pageable);

    Page<ActivoTecnologico> findByCostoAdquisicionBetween(BigDecimal costoAdquisicionStart, BigDecimal costoAdquisicionEnd, Pageable pageable);

    Page<ActivoTecnologico> findByCategoria_NombreLikeIgnoreCase(String nombre, Pageable pageable);



}
