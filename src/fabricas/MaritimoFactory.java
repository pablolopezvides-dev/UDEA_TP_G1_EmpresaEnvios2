package fabricas;

import modelos.DatosEnvio;
import modelos.Envio;
import modelos.Maritimo;

public class MaritimoFactory extends EnvioFactory {
    @Override
    public String getTipo() {
        return Maritimo.TIPO;
    }

    @Override
    public Envio crearEnvio(DatosEnvio datos) {
        return new Maritimo(datos.getCodigo(), datos.getCliente(), datos.getPeso(), datos.getDistancia());
    }
}