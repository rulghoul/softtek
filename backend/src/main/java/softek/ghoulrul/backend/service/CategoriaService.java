package softek.ghoulrul.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import softek.ghoulrul.backend.entities.Categoria;
import softek.ghoulrul.backend.repository.CategoriaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Cacheable("Categorias")
    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }
}
