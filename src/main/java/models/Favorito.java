package models;
public class Favorito {
    private int tipo; // 1 = AM, 0 = FM
    private double estacion;

    public Favorito(int tipo, double estacion) {
        this.tipo = tipo;
        this.estacion = estacion;
    }

    public int getTipo() {
        return tipo;
    }

    public double getEstacion() {
        return estacion;
    }
}
