package modelos;

public class DatosEnvio {
    private final String codigo;
    private final String cliente;
    private final double peso;
    private final double distancia;
    private final String tipo;

    public DatosEnvio(String codigo, String cliente, double peso, double distancia) {
        this(codigo, cliente, peso, distancia, null);
    }

    public DatosEnvio(String codigo, String cliente, double peso, double distancia, String tipo) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.peso = peso;
        this.distancia = distancia;
        this.tipo = tipo;
    }

    public String getCodigo() { return codigo; }
    public String getCliente() { return cliente; }
    public double getPeso() { return peso; }
    public double getDistancia() { return distancia; }
    public String getTipo() { return tipo; }
}

