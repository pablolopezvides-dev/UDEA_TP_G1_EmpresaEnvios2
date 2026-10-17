package fabricas;

import modelos.DatosEnvio;
import modelos.Envio;
import modelos.Terrestre;

public class TerrestreFactory extends EnvioFactory {
    @Override
    public String getTipo() {
        return Terrestre.TIPO;
    }

    @Override
    public Envio crearEnvio(DatosEnvio datos) {
        return new Terrestre(datos.getCodigo(), datos.getCliente(), datos.getPeso(), datos.getDistancia());
    }
}