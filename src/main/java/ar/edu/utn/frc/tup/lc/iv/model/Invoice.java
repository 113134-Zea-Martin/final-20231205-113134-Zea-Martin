package ar.edu.utn.frc.tup.lc.iv.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("invoice_control")
    private InvoiceControl invoiceControl;

    private String letter;

    private LocalDate date;

    @JsonProperty("due_date")
    private LocalDate dueDate;

    private Customer customer;

    private Company company;

    private List<Item> items;

    @JsonProperty("subtotal")
    private BigDecimal subTotal;

    private Tax tax;

    private BigDecimal total;
}
