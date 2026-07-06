package ar.edu.utn.ba.dsi.climalert.Repository;

import ar.edu.utn.ba.dsi.climalert.Entities.ClimaRegistro;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class ClimaRegistroRepository implements IClimaRegistroRepository{
    private final Map<Long, ClimaRegistro> registros = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public ClimaRegistro save(ClimaRegistro registro) {
        if (registro.getId() == null) {
            registro.setId(idGenerator.getAndIncrement());
        }
        registros.put(registro.getId(), registro);
        return registro;
    }

    @Override
    public List<ClimaRegistro> findAll() {
        return List.copyOf(registros.values());
    }

    @Override
    public Optional<ClimaRegistro> findUltimoRegistro() {
        return registros.values().stream()
            .max(Comparator.comparing(ClimaRegistro::getFechaHora));
    }
}
