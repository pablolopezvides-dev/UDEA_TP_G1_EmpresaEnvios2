package fabricas;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class RegistroFabricas {
    private final Map<String, EnvioFactory> fabricas = new LinkedHashMap<>();

    public void registrar(EnvioFactory fabrica) {
        fabricas.put(fabrica.getTipo(), fabrica);
    }

    public Optional<EnvioFactory> buscar(String tipo) {
        return Optional.ofNullable(fabricas.get(tipo));
    }

    public List<String> getTiposDisponibles() {
        return new ArrayList<>(fabricas.keySet());
    }
}