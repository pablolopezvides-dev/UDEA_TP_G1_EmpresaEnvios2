package modelos;

public abstract class Envio {
    private final String codigo;
    private final String cliente;
    private final double peso;
    private final double distancia;

    protected Envio(String codigo, String cliente, double peso, double distancia) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.peso = peso;
        this.distancia = distancia;
    }

    public String getCodigo() { return codigo; }
    public String getCliente() { return cliente; }
    public double getPeso() { return peso; }
    public double getDistancia() { return distancia; }

    public abstract String getTipo();
    public abstract double calcularTarifa(); //Dejamos esta por si algun nuevo tipo de envio requiere
                                            // sus propios parámetros de cálculo

    
    protected final double calcularTarifa(double tarifaPorKm, double recargoPorKg) {
        return (distancia * tarifaPorKm) + (peso * recargoPorKg);
    }
}