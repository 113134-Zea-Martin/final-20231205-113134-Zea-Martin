package ar.edu.utn.frc.tup.lc.iv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tax {

    private String name;
    private BigDecimal percentage;
    private BigDecimal total;
}
