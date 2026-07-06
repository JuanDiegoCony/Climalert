package ar.edu.utn.ba.dsi.climalert.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentDTO {

    @JsonProperty("temp_c")
    public Double tempC;

    @JsonProperty("humidity")
     Double humidity;

    @JsonProperty("condition")
    private ConditionDTO condition;
}
