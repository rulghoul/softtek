package softek.ghoulrul.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import softek.ghoulrul.backend.service.ActivoTecnologicoService;

@Controller
public class ActivoTecnologicoController {

    private ActivoTecnologicoService service;

    @Autowired
    public ActivoTecnologicoController(ActivoTecnologicoService service){
        this.service = service;
    }

}
