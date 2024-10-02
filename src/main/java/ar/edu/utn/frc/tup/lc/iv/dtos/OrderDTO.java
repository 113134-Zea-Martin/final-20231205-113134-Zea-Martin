package ar.edu.utn.frc.tup.lc.iv.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("order_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    private List<OrderItemDTO> items;
}
