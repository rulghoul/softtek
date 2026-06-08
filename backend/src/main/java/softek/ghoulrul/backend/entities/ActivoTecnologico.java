package softek.ghoulrul.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activos_tecnologicos", schema = "inventario")
public class ActivoTecnologico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "identificador_tecnico", length = 100)
    private UUID identificadorTecnico;

    @Column(name = "folio_inventario", length = 100)
    private String folioInventario;

    @Column(name = "numero_de_serie", length = 100, nullable = false)
    private String numeroDeSerie;

    @Column(name = "marca_modelo", length = 100)
    private String marcaModelo;

    @Column(name = "estado", length = 100)
    private String estado;

    @Column(name = "costo_adquisicion")
    private BigDecimal costoAdquisicion;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    private Categoria categoria;
}
