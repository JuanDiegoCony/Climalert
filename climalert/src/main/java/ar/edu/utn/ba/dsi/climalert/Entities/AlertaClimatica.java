package ar.edu.utn.ba.dsi.climalert.Entities;

import lombok.Data;

@Data
public class AlertaClimatica {
    private final ClimaRegistro registro;
    private final String motivo;

    public AlertaClimatica(ClimaRegistro registro, String motivo) {
        this.registro = registro;
        this.motivo = motivo;
    }
}
