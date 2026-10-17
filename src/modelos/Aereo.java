package modelos;

public class Aereo extends Envio {
    public static final String TIPO = "Aéreo";
    private static final double TARIFA_POR_KM = 5000;
    private static final double RECARGO_POR_KG = 4000;

    public Aereo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public double calcularTarifa() {
        return calcularTarifa(TARIFA_POR_KM, RECARGO_POR_KG);
    }
}