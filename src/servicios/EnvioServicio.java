package servicios;

import modelos.Envio;
import java.util.ArrayList;
import java.util.List;

public class EnvioServicio implements IEnvioServicio {
    private final List<Envio> envios = new ArrayList<>();

    @Override
    public void agregarEnvio(Envio envio) {
        envios.add(envio);
    }

    @Override
    public boolean retirarEnvio(String codigo) {
        return envios.removeIf(e -> e.getCodigo().equalsIgnoreCase(codigo));
    }

    @Override
    public List<Envio> obtenerTodos() {
        return new ArrayList<>(envios);
    }

    @Override
    public boolean existeCodigo(String codigo) {
        return envios.stream().anyMatch(e -> e.getCodigo().equalsIgnoreCase(codigo));
    }
}