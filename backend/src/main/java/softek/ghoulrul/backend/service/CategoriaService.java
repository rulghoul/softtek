package softek.ghoulrul.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import softek.ghoulrul.backend.entities.Categoria;
import softek.ghoulrul.backend.repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository =categoriaRepository;
    }

    @Cacheable("Categorias")
    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }
}
