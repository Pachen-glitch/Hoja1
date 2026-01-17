package models;

import models.interfas.Radio;
import java.util.ArrayList;

public class RadioGroup implements Radio {

    private boolean estado;
    private int estacion; // 1 AM, 0 FM
    private double estacionFm; // 87.9 - 107.9
    private int estacionAm;    // 530 - 1610
    private ArrayList<Favorito> favoritos;

    public RadioGroup() {
        this.estado = false;
        this.estacion = 0;
        this.estacionFm = 87.9;
        this.estacionAm = 530;
        this.favoritos = new ArrayList<>(12);
    }

    public void prenderRadio() {
        estado = true;
    }

    public void apagarRadio() {
        estado = false;
    }

  public void guardarEstacion(int numeroBoton) {
    if (estacion == 1) { // AM
        favoritos.add(numeroBoton, new Favorito(1, estacionAm));
    } else { // FM
        favoritos.add(numeroBoton, new Favorito(0, estacionFm));
    }
}

    public void cargarEstacion(int numeroBoton) {
    Favorito fav = favoritos.get(numeroBoton);

    estacion = fav.getTipo(); // cambia AM / FM automáticamente

    if (estacion == 1) {
        estacionAm = (int) fav.getEstacion();
    } else {
        estacionFm = fav.getEstacion();
    }
}

    public void avanzarEstacion() {
        if (estacion == 1) { // AM
            estacionAm += 10;
            if (estacionAm > 1610) {
                estacionAm = 530;
            }
        } else { // FM
            estacionFm += 0.2;
            if (estacionFm > 107.9) {
                estacionFm = 87.9;
            }
        }
    }

    public void cambiarAM() {
        estacion = 1;
    }

    public void cambiarFM() {
        estacion = 0;
    }

    // Getters
    public boolean getEstado() {
        return estado;
    }

    public int getEstacion() {
        return estacion;
    }

    public double getEstacionFm() {
        return estacionFm;
    }

    public int getEstacionAm() {
        return estacionAm;
    }
}
