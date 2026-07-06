package ar.edu.utn.ba.dsi.climalert.Repository;

import ar.edu.utn.ba.dsi.climalert.Entities.ClimaRegistro;
import java.util.List;
import java.util.Optional;

public interface IClimaRegistroRepository {
    ClimaRegistro save(ClimaRegistro registro);
    List<ClimaRegistro> findAll();
    Optional<ClimaRegistro> findUltimoRegistro();
}
