package controller;

import models.RadioGroup;
import service.RadioService;

public class RadioController {

    private RadioService service;
// realiza la comunicacion entre la vista y el servicio, pide las cosas sin embargo no sabe como las obtiene
    public RadioController() {
        RadioGroup radio = new RadioGroup();
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

    public String usarEstacion(int numeroBoton) {
        return service.usarEstacion(numeroBoton);
    }

    public void guardarEstacion(int numeroBoton) {
        service.guardarEstacion(numeroBoton);
    }

    public String obtenerEstacion() {
        return service.getEstacionActual();
    }

    public String toggleBanda() {
        service.toggleBanda();
        return service.getEstacionActual();
    }
}
