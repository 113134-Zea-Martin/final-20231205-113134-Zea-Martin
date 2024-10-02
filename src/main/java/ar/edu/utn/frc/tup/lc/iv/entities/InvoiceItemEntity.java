package ar.edu.utn.frc.tup.lc.iv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "invoice_items")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Completar el resto de los datos del detalle de la factura

    @ManyToOne
    @JoinColumn(name="invoice_id", nullable=false)
    @JsonIgnore
    private InvoiceEntity invoice;
}
