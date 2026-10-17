package controladores;

import fabricas.EnvioFactory;
import fabricas.RegistroFabricas;
import modelos.DatosEnvio;
import modelos.Envio;
import servicios.IEnvioServicio;
import vistas.IEnvioVista;

import java.util.Optional;

public class EnvioControlador {
    private final IEnvioVista vista;
    private final IEnvioServicio servicio;
    private final RegistroFabricas fabricas;

    public EnvioControlador(IEnvioVista vista, IEnvioServicio servicio, RegistroFabricas fabricas) {
        this.vista = vista;
        this.servicio = servicio;
        this.fabricas = fabricas;

        
        this.vista.cargarTipos(this.fabricas.getTiposDisponibles());

        
        this.vista.setAgregarEnvioClick(e -> agregarEnvio());
        this.vista.setRetirarEnvioClick(e -> retirarEnvio());
        this.vista.setCancelarEnvioClick(e -> cancelar());
    }

    private void agregarEnvio() {
        String codigo = vista.getCodigo();
        String cliente = vista.getCliente();
        String pesoStr = vista.getPeso();
        String distanciaStr = vista.getDistancia();
        String tipo = vista.getTipoEnvio();

        if (codigo.isEmpty() || cliente.isEmpty() || pesoStr.isEmpty() || distanciaStr.isEmpty() || tipo == null) {
            vista.mostrarError("Todos los campos son obligatorios.");
            return;
        }

        if (servicio.existeCodigo(codigo)) {
            vista.mostrarError("Ya existe un envío registrado con el código: " + codigo);
            return;
        }

        double peso;
        double distancia;
        try {
            peso = Double.parseDouble(pesoStr);
            distancia = Double.parseDouble(distanciaStr);
            if (peso <= 0 || distancia <= 0) {
                vista.mostrarError("El peso y la distancia deben ser valores mayores a cero.");
                return;
            }
        } catch (NumberFormatException ex) {
            vista.mostrarError("El peso y la distancia deben ser números válidos.");
            return;
        }

        Optional<EnvioFactory> fabrica = fabricas.buscar(tipo);
        if (fabrica.isEmpty()) {
            vista.mostrarError("No existe una fábrica configurada para este tipo de envío.");
            return;
        }

        Envio nuevoEnvio = fabrica.get().crearEnvio(new DatosEnvio(codigo, cliente, peso, distancia));
        servicio.agregarEnvio(nuevoEnvio);

       
        vista.mostrarEnvios(servicio.obtenerTodos());
        vista.limpiarFormulario();
        vista.mostrarMensaje("Envío agregado exitosamente.");
    }

    private void retirarEnvio() {
        String codigo = vista.solicitarCodigoRetiro();
        if (codigo != null && !codigo.trim().isEmpty()) {
            boolean retirado = servicio.retirarEnvio(codigo.trim());
            if (retirado) {
                vista.mostrarEnvios(servicio.obtenerTodos());
                vista.mostrarMensaje("Envío retirado exitosamente.");
            } else {
                vista.mostrarError("No se encontró ningún envío con el código proporcionado.");
            }
        }
    }

    private void cancelar() {
        vista.limpiarFormulario();
    }
}