package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RadioControllerTest {

    private RadioController controller;

    @BeforeEach
    public void setUp() {
        controller = new RadioController();
    }

    @Test
    public void testEncender() {
        // Act
        controller.encender();

        // Assert
        String estado = controller.obtenerEstacion();
        assertTrue(estado.contains("FM") || estado.contains("AM"), "Should be on after encender");
    }

    @Test
    public void testApagar() {
        // Arrange
        controller.encender();

        // Act
        controller.apagar();

        // Assert - we can't directly check if it's off, but we shouldn't get an error
        assertDoesNotThrow(() -> {
            controller.apagar();
        });
    }

    @Test
    public void testObtenerEstacionFM() {
        // Act
        String estado = controller.obtenerEstacion();

        // Assert
        assertTrue(estado.contains("FM"), "Should contain FM");
        assertTrue(estado.contains("87.9"), "Should contain default FM frequency");
    }

    @Test
    public void testObtenerEstacionAM() {
        // Arrange
        controller.encender();
        controller.cambiarEstacion(1);

        // Act
        String estado = controller.obtenerEstacion();

        // Assert
        assertTrue(estado.contains("AM"), "Should contain AM");
        assertTrue(estado.contains("530"), "Should contain default AM frequency");
    }

    @Test
    public void testCambiarEstacionToAM() {
        // Arrange
        controller.encender();

        // Act
        controller.cambiarEstacion(1);

        // Assert
        String estado = controller.obtenerEstacion();
        assertTrue(estado.contains("AM"), "Should change to AM");
    }

    @Test
    public void testCambiarEstacionToFM() {
        // Arrange
        controller.encender();
        controller.cambiarEstacion(1);

        // Act
        controller.cambiarEstacion(0);

        // Assert
        String estado = controller.obtenerEstacion();
        assertTrue(estado.contains("FM"), "Should change to FM");
    }

    @Test
    public void testAdelantarEstacion() {
        // Arrange
        controller.encender();
        String estadoInicial = controller.obtenerEstacion();

        // Act
        controller.adelantarEstacion();

        // Assert
        String estadoFinal = controller.obtenerEstacion();
        assertNotEquals(estadoInicial, estadoFinal, "Station should change after adelantarEstacion");
    }

    @Test
    public void testToggleBanda() {
        // Arrange
        controller.encender();
        String estadoInicial = controller.obtenerEstacion();

        // Act
        String resultado = controller.toggleBanda();

        // Assert
        String estadoFinal = controller.obtenerEstacion();
        if (estadoInicial.contains("FM")) {
            assertTrue(estadoFinal.contains("AM"), "Should toggle to AM");
        } else {
            assertTrue(estadoFinal.contains("FM"), "Should toggle to FM");
        }
        assertEquals(resultado, estadoFinal);
    }

    @Test
    public void testGuardarEstacion() {
        // Arrange
        controller.encender();
        int numeroBoton = 0;

        // Act
        controller.guardarEstacion(numeroBoton);

        // Assert
        assertTrue(controller.tieneFavorito(numeroBoton), "Should have saved favorite");
    }

    @Test
    public void testUsarEstacion() {
        // Arrange
        controller.encender();
        controller.guardarEstacion(0);

        // Act
        String resultado = controller.usarEstacion(0);

        // Assert
        assertTrue(resultado.contains("FM") || resultado.contains("AM"), "Should return station");
    }

    @Test
    public void testTieneFavoritoWhenEmpty() {
        // Act
        boolean result = controller.tieneFavorito(0);

        // Assert
        assertFalse(result, "Should not have favorite");
    }

    @Test
    public void testTieneFavoritoAfterSaving() {
        // Arrange
        controller.encender();
        controller.guardarEstacion(0);

        // Act
        boolean result = controller.tieneFavorito(0);

        // Assert
        assertTrue(result, "Should have favorite");
    }

    @Test
    public void testGuardarMultipleFavoritos() {
        // Arrange
        controller.encender();

        // Act
        controller.guardarEstacion(0);
        controller.guardarEstacion(5);
        controller.guardarEstacion(11);

        // Assert
        assertTrue(controller.tieneFavorito(0));
        assertTrue(controller.tieneFavorito(5));
        assertTrue(controller.tieneFavorito(11));
    }

    @Test
    public void testUsarEstacionEmptyFavorite() {
        // Arrange
        controller.encender();

        // Act
        String resultado = controller.usarEstacion(0);

        // Assert
        assertEquals("Favorito vacío", resultado, "Should return Favorito vacío message");
    }

    @Test
    public void testMultipleOperations() {
        // Arrange & Act
        controller.encender();
        String estado1 = controller.obtenerEstacion();
        controller.adelantarEstacion();
        String estado2 = controller.obtenerEstacion();
        controller.cambiarEstacion(1);
        String estado3 = controller.obtenerEstacion();
        controller.guardarEstacion(0);
        controller.apagar();

        // Assert
        assertNotEquals(estado1, estado2, "Estado should change after adelantarEstacion");
        assertNotEquals(estado2, estado3, "Estado should change after cambiarEstacion");
        assertTrue(controller.tieneFavorito(0), "Favorito should be saved");
    }
}
