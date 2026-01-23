package service;

import models.RadioGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RadioServiceTest {

    private RadioService service;
    private RadioGroup radio;

    @BeforeEach
    void setUp() {
        radio = new RadioGroup();
        service = new RadioService(radio);
    }

    @Test
    void encenderDebePrenderLaRadio() {
        assertFalse(radio.getEstado());

        service.encender();

        assertTrue(radio.getEstado());
    }

    @Test
    void apagarDebeApagarLaRadio() {
        radio.prenderRadio();
        assertTrue(radio.getEstado());

        service.apagar();

        assertFalse(radio.getEstado());
    }

    @Test
    void noDebeCambiarEstacionSiEstaApagada() {
        String antes = service.getEstacionActual();

        service.adelantarEstacion();

        String despues = service.getEstacionActual();
        assertEquals(antes, despues);
    }

    @Test
    void adelantarEstacionDebeCambiarCuandoEstaEncendida() {
        service.encender();

        String antes = service.getEstacionActual();
        service.adelantarEstacion();
        String despues = service.getEstacionActual();

        assertNotEquals(antes, despues);
    }

    @Test
    void cambiarBandaAM() {
        service.encender();

        service.cambiarEstacion(1);

        assertTrue(service.getEstacionActual().contains("AM"));
    }

    @Test
    void cambiarBandaFM() {
        service.encender();

        service.cambiarEstacion(2);

        assertTrue(service.getEstacionActual().contains("FM"));
    }

    @Test
    void toggleBandaDebeCambiarEntreAMyFM() {
        service.encender();

        String antes = service.getEstacionActual();
        service.toggleBanda();
        String despues = service.getEstacionActual();

        assertNotEquals(
                antes.contains("FM"),
                despues.contains("FM")
        );
    }

    @Test
    void guardarYUsarFavorito() {
        service.encender();

        String guardado = service.guardarEstacion(1);
        assertTrue(guardado.startsWith("Guardado"));

        String estacion = service.usarEstacion(1);
        assertNotNull(estacion);
        assertTrue(
                estacion.contains("FM") || estacion.contains("AM")
        );
    }

    @Test
    void usarFavoritoVacioDevuelveMensaje() {
        service.encender();

        String respuesta = service.usarEstacion(2);

        assertEquals("Favorito vacío", respuesta);
    }

    @Test
    void usarFavoritoConRadioApagada() {
        String respuesta = service.usarEstacion(1);

        assertEquals("APAGADA", respuesta);
    }

    @Test
    void tieneFavoritoDebeFuncionar() {
        service.encender();

        assertFalse(service.tieneFavorito(1));

        service.guardarEstacion(1);

        assertTrue(service.tieneFavorito(1));
    }
}
