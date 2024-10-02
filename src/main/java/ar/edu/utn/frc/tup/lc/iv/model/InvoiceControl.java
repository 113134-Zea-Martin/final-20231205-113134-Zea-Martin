package ar.edu.utn.frc.tup.lc.iv.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceControl {

    @JsonProperty("control_type")
    private String controlType;

    @JsonProperty("control_number")
    private String controlNumber;

}
