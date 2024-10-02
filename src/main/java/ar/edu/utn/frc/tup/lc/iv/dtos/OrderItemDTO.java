package ar.edu.utn.frc.tup.lc.iv.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItemDTO {

    private Long id;

    @JsonProperty("product_id")
    private Long productId;

    private Integer quantity;
}
