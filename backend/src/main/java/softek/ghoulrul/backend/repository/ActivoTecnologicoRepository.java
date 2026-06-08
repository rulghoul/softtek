package softek.ghoulrul.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softek.ghoulrul.backend.entities.ActivoTecnologico;
import softek.ghoulrul.backend.entities.Categoria;

import java.util.UUID;

public interface ActivoTecnologicoRepository  extends JpaRepository<ActivoTecnologico, UUID> {
}
