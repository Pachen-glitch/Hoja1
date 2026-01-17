package service;

import models.RadioGroup;

public class RadioService {

    private RadioGroup radio;

    public RadioService(RadioGroup radio) {
        this.radio = radio;
    }

    public void encender() {
        if (radio.getEstado()) {
            System.out.println("Ya está encendida");
        } else { 
            radio.prenderRadio();
        }
    }

    public void apagar() {
        if (!radio.getEstado()) {
            System.out.println("Ya está apagada");
        } else {
            radio.apagarRadio();
        }
    }

    public void cambiarEstacion(int button) {
        if (!validar()) return;
        if (button == 1) {
            radio.cambiarAM();
        } else {
            radio.cambiarFM();
        }
    }

    public void adelantarEstacion() {
        if (!validar()) return;
        radio.avanzarEstacion();
    }

    private boolean validar() {
        if (!radio.getEstado()) {
            System.out.println("La radio está apagada");
            return false;
        }
        return true;
    }

    public void cargarEstacion(int numeroBoton) {
        if (!validar()) return;
        radio.cargarEstacion(numeroBoton);
    }
    public void guardarEstacion(int numeroBoton) {
        if (!validar()) return;
        radio.guardarEstacion(numeroBoton);
    }
    
}
