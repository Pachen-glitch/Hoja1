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
    
        while (favoritos.size() <= numeroBoton) {
            favoritos.add(null);
        }

        if (estacion == 1) { // AM
            favoritos.set(numeroBoton, new Favorito(1, estacionAm));
        } else { // FM
            favoritos.set(numeroBoton, new Favorito(0, estacionFm));
        }
    }

    @Override
    public void cargarEstacion(int numeroBoton) {
        if (numeroBoton >= favoritos.size() || favoritos.get(numeroBoton) == null) {
            throw new IllegalStateException("Favorito vacío");
        }

        Favorito fav = favoritos.get(numeroBoton);
        estacion = fav.getTipo();

        if (estacion == 1) { // AM
            estacionAm = (int) fav.getEstacion();
        } else { // FM
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
