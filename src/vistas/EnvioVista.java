package vistas;

import modelos.Envio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class EnvioVista extends JFrame implements IEnvioVista {
    private JTextField txtCodigo;
    private JTextField txtCliente;
    private JTextField txtPeso;
    private JTextField txtDistancia;
    private JComboBox<String> cbTipoEnvio;
    private JButton btnIconoAgregar;
    private JButton btnIconoRetirar;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JTable tablaEnvios;
    private DefaultTableModel modeloTabla;

    public EnvioVista() {
        setTitle("Operador Logístico");
        setSize(720, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        btnIconoAgregar = new JButton();
        btnIconoRetirar = new JButton();

        asignarIcono(btnIconoAgregar, "/iconos/Agregar.png", " Agregar ");
        asignarIcono(btnIconoRetirar, "/iconos/Retirar.png", " Retirar ");

        btnIconoAgregar.setToolTipText("Nuevo Envío");
        btnIconoRetirar.setToolTipText("Retirar Envío");

        toolBar.add(btnIconoAgregar);
        toolBar.add(btnIconoRetirar);
        add(toolBar, BorderLayout.NORTH);

       
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Número"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField(12);
        panelFormulario.add(txtCodigo, gbc);

        gbc.gridx = 2;
        panelFormulario.add(new JLabel("Tipo"), gbc);
        gbc.gridx = 3;
        cbTipoEnvio = new JComboBox<>();
        panelFormulario.add(cbTipoEnvio, gbc);

    
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Cliente"), gbc);
        gbc.gridx = 1;
        txtCliente = new JTextField(12);
        panelFormulario.add(txtCliente, gbc);

        gbc.gridx = 2;
        panelFormulario.add(new JLabel("Distancia en Km"), gbc);
        gbc.gridx = 3;
        txtDistancia = new JTextField(12);
        panelFormulario.add(txtDistancia, gbc);

        
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Peso"), gbc);
        gbc.gridx = 1;
        txtPeso = new JTextField(12);
        panelFormulario.add(txtPeso, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 2; gbc.gridy = 2; gbc.gridwidth = 2;
        panelFormulario.add(panelBotones, gbc);

        
        String[] columnas = {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEnvios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEnvios);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(panelFormulario, BorderLayout.NORTH);
        panelCentro.add(scrollPane, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);
    }

    private void asignarIcono(JButton boton, String ruta, String textoAlterno) {
    URL recurso = getClass().getResource(ruta);
    if (recurso != null) {
        boton.setIcon(new ImageIcon(recurso));
        boton.setText("");
    } else {
        boton.setText(textoAlterno);
    }
}

    @Override
    public String getCodigo() { return txtCodigo.getText().trim(); }

    @Override
    public String getCliente() { return txtCliente.getText().trim(); }

    @Override
    public String getPeso() { return txtPeso.getText().trim(); }

    @Override
    public String getDistancia() { return txtDistancia.getText().trim(); }

    @Override
    public String getTipoEnvio() { return (String) cbTipoEnvio.getSelectedItem(); }

    @Override
    public void cargarTipos(List<String> tipos) {
        cbTipoEnvio.removeAllItems();
        for (String tipo : tipos) {
            cbTipoEnvio.addItem(tipo);
        }
    }

    @Override
    public void mostrarEnvios(List<Envio> envios) {
        modeloTabla.setRowCount(0);
        NumberFormat formatoNumero = NumberFormat.getNumberInstance(Locale.US);
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US);

        for (Envio envio : envios) {
            Object[] fila = {
                    envio.getTipo(),
                    envio.getCodigo(),
                    envio.getCliente(),
                    formatoNumero.format(envio.getPeso()),
                    formatoNumero.format(envio.getDistancia()),
                    formatoMoneda.format(envio.calcularTarifa())
            };
            modeloTabla.addRow(fila);
        }
    }

    @Override
    public String solicitarCodigoRetiro() {
        return JOptionPane.showInputDialog(this, "Ingrese el código del envío a retirar:", "Retirar Envío", JOptionPane.QUESTION_MESSAGE);
    }

    @Override
    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        if (cbTipoEnvio.getItemCount() > 0) {
            cbTipoEnvio.setSelectedIndex(0);
        }
        txtCodigo.requestFocus();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void mostrar() {
        setVisible(true);
    }

    @Override
    public void setAgregarEnvioClick(ActionListener escuchador) {
        btnGuardar.addActionListener(escuchador);
        btnIconoAgregar.addActionListener(escuchador);
    }

    @Override
    public void setRetirarEnvioClick(ActionListener escuchador) {
        btnIconoRetirar.addActionListener(escuchador);
    }

    @Override
    public void setCancelarEnvioClick(ActionListener escuchador) {
        btnCancelar.addActionListener(escuchador);
    }
}