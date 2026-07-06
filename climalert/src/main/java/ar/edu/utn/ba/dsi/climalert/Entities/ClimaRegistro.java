package ar.edu.utn.ba.dsi.climalert.Entities;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ClimaRegistro {
    private Long id;

    private String ciudad;
    private Double temperatura;
    private Double humedad;
    private String condicionTexto;
    private LocalDateTime fechaHora;

    public ClimaRegistro(String ciudad, Double temperatura,
                         Double humedad, String condicionTexto, LocalDateTime fechaHora) {
        this.ciudad = ciudad;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.condicionTexto = condicionTexto;
        this.fechaHora = fechaHora;
    }

    public boolean esCritico() {
        return temperatura != null && temperatura > 35 && humedad != null && humedad > 60;
    }
}
