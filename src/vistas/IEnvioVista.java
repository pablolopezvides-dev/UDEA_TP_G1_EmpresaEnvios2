package vistas;

import modelos.Envio;
import java.awt.event.ActionListener;
import java.util.List;


public interface IEnvioVista {
    String getCodigo();
    String getCliente();
    String getPeso();
    String getDistancia();
    String getTipoEnvio();
    
    void cargarTipos(List<String> tipos);
    void mostrarEnvios(List<Envio> envios);
    String solicitarCodigoRetiro();
    void limpiarFormulario();
    void mostrarMensaje(String mensaje);
    void mostrarError(String mensaje);
    void mostrar();

    void setAgregarEnvioClick(ActionListener escuchador);
    void setRetirarEnvioClick(ActionListener escuchador);
    void setCancelarEnvioClick(ActionListener escuchador);
}