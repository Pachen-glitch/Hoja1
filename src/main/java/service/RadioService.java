package service;

import models.RadioGroup;

public class RadioService {

    private RadioGroup radio;
// logica de la radio\
// El service tiene toda la logica de la aplicacion 
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

    public void toggleBanda() {
        if (!validar()) return;

        if (radio.getEstacion() == 1) {
            radio.cambiarFM();
        } else {
            radio.cambiarAM();
        }
    }

    public void adelantarEstacion() {
        if (!validar()) return;
        radio.avanzarEstacion();
    }
// metodo para validar si la radio esta encendida
    private boolean validar() {
        if (!radio.getEstado()) {
            System.out.println("La radio está apagada");
            return false;
        }
        return true;
    }

    public String usarEstacion(int numeroBoton) {
        if (!validar()) return "APAGADA";

        try {
            radio.cargarEstacion(numeroBoton);
            return getEstacionActual();
        } catch (IllegalStateException e) {
            return "Favorito vacío";
        }
    }

    public String guardarEstacion(int numeroBoton) {
        if (!validar()) return "APAGADA";
        radio.guardarEstacion(numeroBoton);
        return "Guardado: " + getEstacionActual();
    }
// Metodos que utiliza el controlador para dar respuesta al cliente
    public String getEstacionActual() {
        if (radio.getEstacion() == 1) { // AM
            return radio.getEstacionAm() + " AM";
        } else { // FM
            return String.format("%.1f FM", radio.getEstacionFm());
        }
    }
}
