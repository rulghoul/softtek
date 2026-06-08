package softek.ghoulrul.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivoTecnologicoRequestDTO {

    @Size(max = 100, message = "El identificador técnico no puede exceder 100 caracteres")
    private String identificadorTecnico;

    @Size(max = 100, message = "El folio de inventario no puede exceder 100 caracteres")
    private String folioInventario;

    @NotBlank(message = "El número de serie es obligatorio")
    @Size(max = 100, message = "El número de serie no puede exceder 100 caracteres")
    private String numeroDeSerie;

    @Size(max = 100, message = "La marca/modelo no puede exceder 100 caracteres")
    private String marcaModelo;

    @Size(max = 100, message = "El estado no puede exceder 100 caracteres")
    private String estado;

    @DecimalMin(value = "0.0", inclusive = true, message = "El costo no puede ser negativo")
    private BigDecimal costoAdquisicion;

    private LocalDateTime fechaHora;

    @NotNull(message = "El ID de la categoría es obligatorio")
    private Long categoriaId;
}
