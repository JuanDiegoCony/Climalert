package ar.edu.utn.ba.dsi.climalert.Service.Impl;

import ar.edu.utn.ba.dsi.climalert.Entities.AlertaClimatica;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final String[] DESTINATARIOS = {
        "admin@clima.com",
        "emergencias@clima.com",
        "meteorologia@clima.com"
    };


    private JavaMailSender mailSender = null;

    @Value("${spring.mail.username}")
    private String remitente;


    public void enviarAlerta(AlertaClimatica alerta) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom(remitente);
        mensaje.setTo(DESTINATARIOS);
        mensaje.setSubject("Alerta climática detectada");
        mensaje.setText(construirCuerpo(alerta));
        mailSender.send(mensaje);
    }

    private String construirCuerpo(AlertaClimatica alerta) {
        var registro = alerta.getRegistro();
        return """
                Se detectó una condición climática crítica.
                Motivo: %s
                Ciudad: %s
                Temperatura: %.1f°C
                Humedad: %.1f%%
                Condición: %s
                Fecha y hora: %s
                """.formatted(
            alerta.getMotivo(),
            registro.getCiudad(),
            registro.getTemperatura(),
            registro.getHumedad(),
            registro.getCondicionTexto(),
            registro.getFechaHora()
        );
    }
}
