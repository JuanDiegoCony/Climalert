package ar.edu.utn.ba.dsi.climalert.Scheduler;


import ar.edu.utn.ba.dsi.climalert.Service.Impl.ClimalertService;
import org.springframework.scheduling.annotation.Scheduled;

public class ClimalertScheduler {
    private final ClimalertService climaService;
    private boolean flagAnalizado;

    public ClimalertScheduler(ClimalertService climaService) {
        this.climaService = climaService;
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void recolectarClima() {
        climaService.generarReporte();
        flagAnalizado = false;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void analizarAlertas() {
        if(!flagAnalizado) {
            climaService.analizarYnotificar();
            flagAnalizado = true;
        }
    }
}
