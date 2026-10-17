package fabricas;

import modelos.Aereo;
import modelos.DatosEnvio;
import modelos.Envio;

public class AereoFactory extends EnvioFactory {
    @Override
    public String getTipo() {
        return Aereo.TIPO;
    }

    @Override
    public Envio crearEnvio(DatosEnvio datos) {
        return new Aereo(datos.getCodigo(), datos.getCliente(), datos.getPeso(), datos.getDistancia());
    }
}