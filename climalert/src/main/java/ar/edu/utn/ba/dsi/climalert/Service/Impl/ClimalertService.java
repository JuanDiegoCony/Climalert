package ar.edu.utn.ba.dsi.climalert.Service.Impl;


import ar.edu.utn.ba.dsi.climalert.Entities.AlertaClimatica;
import ar.edu.utn.ba.dsi.climalert.Entities.ClimaRegistro;
import ar.edu.utn.ba.dsi.climalert.Repository.IClimaRegistroRepository;
import ar.edu.utn.ba.dsi.climalert.Service.IClimalertService;
import ar.edu.utn.ba.dsi.climalert.Service.WeatherAPI.WeatherAPI;
import ar.edu.utn.ba.dsi.climalert.dtos.WeatherApiResponseDTO;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ClimalertService implements IClimalertService {

  private final WeatherAPI weatherApiClient;
  private final IClimaRegistroRepository climaRegistroRepository;
  private final EmailService emailService;

  private String ciudad = "Buenos Aires";

  public ClimalertService(WeatherAPI weatherApiClient,
                      IClimaRegistroRepository climaRegistroRepository,
                      EmailService emailService) {
    this.weatherApiClient = weatherApiClient;
    this.climaRegistroRepository = climaRegistroRepository;
    this.emailService = emailService;
  }

  public void generarReporte() {
    WeatherApiResponseDTO respuesta = weatherApiClient.obtenerClimaActual();
    var current = respuesta.getCurrent();

    ClimaRegistro registro = new ClimaRegistro(
        ciudad,
        current.getTempC(),
        current.getHumidity(),
        current.getCondition().getText(),
        LocalDateTime.now()
    );
    climaRegistroRepository.save(registro);
  }

  public void analizarYnotificar() {
    climaRegistroRepository.findUltimoRegistro()
        .filter(ClimaRegistro::esCritico)
        .ifPresent(registro -> {
          String motivo = "Temperatura y humedad por encima de los umbrales permitidos";
          emailService.enviarAlerta(new AlertaClimatica(registro, motivo));
        });
  }
}
