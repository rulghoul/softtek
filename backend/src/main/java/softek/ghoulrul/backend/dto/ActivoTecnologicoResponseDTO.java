package softek.ghoulrul.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivoTecnologicoResponseDTO {
    private UUID id;
    private String identificadorTecnico;
    private String folioInventario;
    private String numeroDeSerie;
    private String marcaModelo;
    private String estado;
    private BigDecimal costoAdquisicion;
    private LocalDateTime fechaHora;
    private String categoria;
}