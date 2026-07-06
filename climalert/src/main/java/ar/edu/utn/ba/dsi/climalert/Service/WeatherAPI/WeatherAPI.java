package ar.edu.utn.ba.dsi.climalert.Service.WeatherAPI;

import ar.edu.utn.ba.dsi.climalert.Config.WeatherApiProperties;
import ar.edu.utn.ba.dsi.climalert.dtos.WeatherApiResponseDTO;
import java.net.URI;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherAPI implements IWeatherAPI{

    private final RestTemplate restTemplate;
    private final WeatherApiProperties properties;


    public WeatherAPI(RestTemplate restTemplate, WeatherApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public WeatherApiResponseDTO obtenerClimaActual() {
        URI uri = UriComponentsBuilder.
            fromUriString(properties.getBaseUrl()).
            path("/current.json?").
            queryParam("key", "b60711cc11cb48b8970160724260607").
            queryParam("q", "Argentina").
            queryParam("currrent_fields", "string").build().toUri();

        return restTemplate.getForObject(uri, WeatherApiResponseDTO.class);
    }
}
