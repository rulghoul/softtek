package softek.ghoulrul.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softek.ghoulrul.backend.dto.ActivoTecnologicoResponseDTO;
import softek.ghoulrul.backend.repository.ActivoTecnologicoRepository;

import java.util.List;

@Service
public class ActivoTecnologicoService {

    private ActivoTecnologicoRepository repository;

    @Autowired
    public ActivoTecnologicoService(ActivoTecnologicoRepository repository){
        this.repository = repository;
    }

    public List<ActivoTecnologicoResponseDTO> getAll(){
        var activos = repository.findAll();
        return activos.stream()
                .map(activo ->
                        new ActivoTecnologicoResponseDTO(
                                activo.getIdentificadorTecnico().toString(),
                                activo.getFolioInventario(),
                                activo.getNumeroDeSerie(),
                                activo.getMarcaModelo(),
                                activo.getEstado(),
                                activo.getCostoAdquisicion(),
                                activo.getFechaHora(),
                                activo.getCategoria().getNombre()
                        ))
                .toList();
    }
}
