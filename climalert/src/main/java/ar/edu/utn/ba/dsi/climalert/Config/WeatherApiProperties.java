package ar.edu.utn.ba.dsi.climalert.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather-api")
@Data
public class WeatherApiProperties {
    private String baseUrl;
}
