package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RadioGroupTest {

    private RadioGroup radio;

    @BeforeEach
    public void setUp() {
        radio = new RadioGroup();
    }

    @Test
    public void testRadioGroupInitialState() {
        // Assert
        assertFalse(radio.getEstado(), "Radio should be off by default");
        assertEquals(0, radio.getEstacion(), "Default estacion should be 0 (FM)");
        assertEquals(87.9, radio.getEstacionFm(), "Default FM frequency should be 87.9");
        assertEquals(530, radio.getEstacionAm(), "Default AM frequency should be 530");
    }

    @Test
    public void testPrenderRadio() {
        // Act
        radio.prenderRadio();

        // Assert
        assertTrue(radio.getEstado(), "Radio should be on after calling prenderRadio");
    }

    @Test
    public void testApagarRadio() {
        // Arrange
        radio.prenderRadio();

        // Act
        radio.apagarRadio();

        // Assert
        assertFalse(radio.getEstado(), "Radio should be off after calling apagarRadio");
    }

    @Test
    public void testCambiarFM() {
        // Act
        radio.cambiarFM();

        // Assert
        assertEquals(0, radio.getEstacion(), "Estacion should be 0 (FM) after cambiarFM");
    }

    @Test
    public void testCambiarAM() {
        // Act
        radio.cambiarAM();

        // Assert
        assertEquals(1, radio.getEstacion(), "Estacion should be 1 (AM) after cambiarAM");
    }

    @Test
    public void testAvanzarEstacionFM() {
        // Arrange
        radio.cambiarFM();
        double initialFrequency = radio.getEstacionFm();

        // Act
        radio.avanzarEstacion();

        // Assert
        assertEquals(initialFrequency + 0.2, radio.getEstacionFm(), 0.01);
    }

    @Test
    public void testAvanzarEstacionFMWrapAround() {
        // Arrange
        radio.cambiarFM();
        // Set frequency close to maximum to trigger wrap around
        double maxFM = 107.9;
        // We need to set the frequency, but there's no setter, so we advance multiple times
        while (radio.getEstacionFm() < maxFM) {
            radio.avanzarEstacion();
        }

        // Act
        radio.avanzarEstacion();

        // Assert
        assertEquals(87.9, radio.getEstacionFm(), "FM should wrap around to 87.9");
    }

    @Test
    public void testAvanzarEstacionAM() {
        // Arrange
        radio.cambiarAM();
        int initialFrequency = radio.getEstacionAm();

        // Act
        radio.avanzarEstacion();

        // Assert
        assertEquals(initialFrequency + 10, radio.getEstacionAm());
    }

    @Test
    public void testAvanzarEstacionAMWrapAround() {
        // Arrange
        radio.cambiarAM();
        // Set frequency close to maximum
        while (radio.getEstacionAm() < 1610) {
            radio.avanzarEstacion();
        }

        // Act
        radio.avanzarEstacion();

        // Assert
        assertEquals(530, radio.getEstacionAm(), "AM should wrap around to 530");
    }

    @Test
    public void testGuardarEstacionFM() {
        // Arrange
        radio.cambiarFM();
        int numeroBoton = 0;

        // Act
        radio.guardarEstacion(numeroBoton);

        // Assert
        assertTrue(radio.tieneFavorito(numeroBoton), "Should have a favorite at position 0");
    }

    @Test
    public void testGuardarEstacionAM() {
        // Arrange
        radio.cambiarAM();
        int numeroBoton = 0;

        // Act
        radio.guardarEstacion(numeroBoton);

        // Assert
        assertTrue(radio.tieneFavorito(numeroBoton), "Should have a favorite at position 0");
    }

    @Test
    public void testGuardarMultipleFavoritos() {
        // Arrange
        radio.cambiarFM();

        // Act
        radio.guardarEstacion(0);
        radio.guardarEstacion(5);
        radio.guardarEstacion(11);

        // Assert
        assertTrue(radio.tieneFavorito(0));
        assertTrue(radio.tieneFavorito(5));
        assertTrue(radio.tieneFavorito(11));
    }

    @Test
    public void testCargarEstacion() {
        // Arrange
        radio.cambiarFM();
        double frequencyToSave = radio.getEstacionFm();
        radio.guardarEstacion(0);

        // Change frequency
        radio.avanzarEstacion();

        // Act
        radio.cargarEstacion(0);

        // Assert
        assertEquals(frequencyToSave, radio.getEstacionFm());
    }

    @Test
    public void testCargarEstacionInvalido() {
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            radio.cargarEstacion(0);
        }, "Should throw IllegalStateException for empty favorite");
    }

    @Test
    public void testTieneFavoritoEmpty() {
        // Assert
        assertFalse(radio.tieneFavorito(0), "Should not have favorite at empty position");
    }

    @Test
    public void testTieneFavoritoAfterSaving() {
        // Arrange
        radio.guardarEstacion(3);

        // Assert
        assertTrue(radio.tieneFavorito(3), "Should have favorite after saving");
    }
}
