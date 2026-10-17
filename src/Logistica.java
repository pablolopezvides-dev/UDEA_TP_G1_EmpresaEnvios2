import controladores.EnvioControlador;
import fabricas.AereoFactory;
import fabricas.MaritimoFactory;
import fabricas.RegistroFabricas;
import fabricas.TerrestreFactory;
import servicios.EnvioServicio;
import servicios.IEnvioServicio;
import vistas.EnvioVista;
import vistas.IEnvioVista;

import javax.swing.SwingUtilities;

public class Logistica {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IEnvioVista vista = new EnvioVista();
            IEnvioServicio servicio = new EnvioServicio();

           
            RegistroFabricas fabricas = new RegistroFabricas();
            fabricas.registrar(new TerrestreFactory());
            fabricas.registrar(new AereoFactory());
            fabricas.registrar(new MaritimoFactory());

            new EnvioControlador(vista, servicio, fabricas);
            vista.mostrar();
        });
    }
}