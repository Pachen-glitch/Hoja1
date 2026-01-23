package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RadioControllerTest {

    private RadioController controller;

    @BeforeEach
    void setUp() {
        controller = new RadioController();
    }

    @Test
    void testEncender() {
        controller.encender();

        String estado = controller.obtenerEstacion();

        assertNotNull(estado);
        assertTrue(
            estado.contains("FM") || estado.contains("AM"),
            "La radio debería estar encendida en AM o FM"
        );
    }

    @Test
    void testApagarNoLanzaExcepcion() {
        controller.encender();

        assertDoesNotThrow(() -> controller.apagar());
    }

    @Test
    void testCambiarEstacion() {
        controller.encender();

        String antes = controller.obtenerEstacion();
        controller.adelantarEstacion();
        String despues = controller.obtenerEstacion();

        assertNotEquals(antes, despues, "La estación debería cambiar");
    }

    @Test
    void testToggleBanda() {
        controller.encender();

        String antes = controller.obtenerEstacion();
        String despues = controller.toggleBanda();

        assertNotEquals(
            antes.contains("FM"),
            despues.contains("FM"),
            "La banda debería cambiar entre AM y FM"
        );
    }

    @Test
    void testGuardarYUsarFavorito() {
        controller.encender();

        controller.guardarEstacion(1);
        assertTrue(controller.tieneFavorito(1));

        String estacion = controller.usarEstacion(1);
        assertNotNull(estacion);
    }
}
