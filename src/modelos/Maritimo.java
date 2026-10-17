package modelos;

public class Maritimo extends Envio {
    public static final String TIPO = "Marítimo";
    private static final double TARIFA_POR_KM = 800;
    private static final double RECARGO_POR_KG = 1000;

    public Maritimo(String codigo, String cliente, double peso, double distancia) {
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