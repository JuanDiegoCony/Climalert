package ar.edu.utn.ba.dsi.climalert.Service.WeatherAPI;


import ar.edu.utn.ba.dsi.climalert.dtos.WeatherApiResponseDTO;

public interface IWeatherAPI {
    WeatherApiResponseDTO obtenerClimaActual();
}
