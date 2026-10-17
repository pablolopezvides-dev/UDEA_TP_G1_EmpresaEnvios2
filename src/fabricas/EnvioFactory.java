package fabricas;

import modelos.DatosEnvio;
import modelos.Envio;


public abstract class EnvioFactory {
    public abstract String getTipo();
    public abstract Envio crearEnvio(DatosEnvio datos);
}