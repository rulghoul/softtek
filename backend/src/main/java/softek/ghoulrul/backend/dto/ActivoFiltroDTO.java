package softek.ghoulrul.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivoFiltroDTO {
    private String numeroDeSerie;
    private String marcaModelo;
    private String estado;
    private BigDecimal costoMin;
    private BigDecimal costoMax;
    private String nombreCategoria; // Para buscar por el texto de la categoría
}