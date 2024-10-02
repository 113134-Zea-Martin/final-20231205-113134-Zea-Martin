package ar.edu.utn.frc.tup.lc.iv.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Completar el resto de los datos de la cabecera de la factura

    @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "invoice")
    private List<InvoiceItemEntity> items;
}
