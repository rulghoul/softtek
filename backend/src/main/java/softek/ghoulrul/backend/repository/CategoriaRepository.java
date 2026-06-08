package softek.ghoulrul.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softek.ghoulrul.backend.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
