package servicios;

import modelos.Envio;
import java.util.List;

public interface IEnvioServicio {
    void agregarEnvio(Envio envio);
    boolean retirarEnvio(String codigo);
    List<Envio> obtenerTodos();
    boolean existeCodigo(String codigo);
}