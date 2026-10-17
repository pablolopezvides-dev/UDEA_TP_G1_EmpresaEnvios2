package modelos;

public class Terrestre extends Envio {
    public static final String TIPO = "Terrestre";
    private static final double TARIFA_POR_KM = 1500;
    private static final double RECARGO_POR_KG = 2000;

    public Terrestre(String codigo, String cliente, double peso, double distancia) {
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