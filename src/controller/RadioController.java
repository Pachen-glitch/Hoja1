package controller;

import models.RadioGroup;
import service.RadioService;

public class RadioController {

    private RadioService service;

    public RadioController() {
        RadioGroup radio = new RadioGroup(); // modelo 
        service = new RadioService(radio);
    }

    public void encender() {
        service.encender();
    }

    public void apagar() {
        service.apagar();
    }

    public void cambiarEstacion(int button) {
        service.cambiarEstacion(button);
    }

    public void adelantarEstacion() {
        service.adelantarEstacion();
    }
    public void cargarEstacion(int numeroBoton) {
        service.cargarEstacion(numeroBoton);
    }
    public void guardarEstacion(int numeroBoton) {
        service.guardarEstacion(numeroBoton);
    }

    
}
