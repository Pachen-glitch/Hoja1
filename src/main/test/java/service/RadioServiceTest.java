package service;

import models.RadioGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RadioServiceTest {

    private RadioService service;
    private RadioGroup radio;

    @BeforeEach
    public void setUp() {
        radio = new RadioGroup();
        service = new RadioService(radio);
    }

    @Test
    public void testEncenderRadioWhenOff() {
        // Arrange
        assertFalse(radio.getEstado());

        // Act
        service.encender();

        // Assert
        assertTrue(radio.getEstado(), "Radio should be on after encender");
    }

    @Test
    public void testEncenderRadioWhenAlreadyOn() {
        // Arrange
        radio.prenderRadio();
        assertTrue(radio.getEstado());

        // Act
        service.encender();

        // Assert
        assertTrue(radio.getEstado(), "Radio should remain on");
    }

    @Test
    public void testApagarRadioWhenOn() {
        // Arrange
        radio.prenderRadio();
        assertTrue(radio.getEstado());

        // Act
        service.apagar();

        // Assert
        assertFalse(radio.getEstado(), "Radio should be off after apagar");
    }

    @Test
    public void testApagarRadioWhenAlreadyOff() {
        // Arrange
        assertFalse(radio.getEstado());

        // Act
        service.apagar();

        // Assert
        assertFalse(radio.getEstado(), "Radio should remain off");
    }

    @Test
    public void testCambiarEstacionToAMWhenRadioOn() {
        // Arrange
        radio.prenderRadio();

        // Act
        service.cambiarEstacion(1);

        // Assert
        assertEquals(1, radio.getEstacion(), "Should change to AM");
    }

    @Test
    public void testCambiarEstacionToFMWhenRadioOn() {
        // Arrange
        radio.prenderRadio();
        radio.cambiarAM();

        // Act
        service.cambiarEstacion(0);

        // Assert
        assertEquals(0, radio.getEstacion(), "Should change to FM");
    }

    @Test
    public void testCambiarEstacionWhenRadioOff() {
        // Arrange
        assertFalse(radio.getEstado());
        int initialEstacion = radio.getEstacion();

        // Act
        service.cambiarEstacion(1);

        // Assert
        assertEquals(initialEstacion, radio.getEstacion(), "Should not change when radio is off");
    }

    @Test
    public void testToggleBandaFromFMToAM() {
        // Arrange
        radio.prenderRadio();
        radio.cambiarFM();

        // Act
        service.toggleBanda();

        // Assert
        assertEquals(1, radio.getEstacion(), "Should change to AM");
    }

    @Test
    public void testToggleBandaFromAMToFM() {
        // Arrange
        radio.prenderRadio();
        radio.cambiarAM();

        // Act
        service.toggleBanda();

        // Assert
        assertEquals(0, radio.getEstacion(), "Should change to FM");
    }

    @Test
    public void testToggleBandaWhenRadioOff() {
        // Arrange
        assertFalse(radio.getEstado());
        int initialEstacion = radio.getEstacion();

        // Act
        service.toggleBanda();

        // Assert
        assertEquals(initialEstacion, radio.getEstacion(), "Should not toggle when radio is off");
    }

    @Test
    public void testAdelantarEstacionWhenRadioOn() {
        // Arrange
        radio.prenderRadio();
        radio.cambiarFM();
        double initialFrequency = radio.getEstacionFm();

        // Act
        service.adelantarEstacion();

        // Assert
        assertEquals(initialFrequency + 0.2, radio.getEstacionFm(), 0.01);
    }

    @Test
    public void testAdelantarEstacionWhenRadioOff() {
        // Arrange
        assertFalse(radio.getEstado());
        double initialFrequency = radio.getEstacionFm();

        // Act
        service.adelantarEstacion();

        // Assert
        assertEquals(initialFrequency, radio.getEstacionFm(), "Should not advance when radio is off");
    }

    @Test
    public void testGetEstacionActualFM() {
        // Arrange
        radio.cambiarFM();

        // Act
        String result = service.getEstacionActual();

        // Assert
        assertTrue(result.contains("FM"), "Should contain FM");
        assertTrue(result.contains("87.9"), "Should contain frequency");
    }

    @Test
    public void testGetEstacionActualAM() {
        // Arrange
        radio.cambiarAM();

        // Act
        String result = service.getEstacionActual();

        // Assert
        assertTrue(result.contains("AM"), "Should contain AM");
        assertTrue(result.contains("530"), "Should contain frequency");
    }

    @Test
    public void testGuardarEstacionWhenRadioOn() {
        // Arrange
        radio.prenderRadio();

        // Act
        String result = service.guardarEstacion(0);

        // Assert
        assertTrue(result.contains("Guardado"), "Should return Guardado message");
        assertTrue(radio.tieneFavorito(0), "Should have favorite saved");
    }

    @Test
    public void testGuardarEstacionWhenRadioOff() {
        // Arrange
        assertFalse(radio.getEstado());

        // Act
        String result = service.guardarEstacion(0);

        // Assert
        assertEquals("APAGADA", result, "Should return APAGADA when radio is off");
        assertFalse(radio.tieneFavorito(0), "Should not save favorite when radio is off");
    }

    @Test
    public void testUsarEstacionWhenRadioOn() {
        // Arrange
        radio.prenderRadio();
        radio.guardarEstacion(0);

        // Act
        String result = service.usarEstacion(0);

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("FM") || result.contains("AM"));
    }

    @Test
    public void testUsarEstacionWhenRadioOff() {
        // Arrange
        assertFalse(radio.getEstado());

        // Act
        String result = service.usarEstacion(0);

        // Assert
        assertEquals("APAGADA", result, "Should return APAGADA when radio is off");
    }

    @Test
    public void testUsarEstacionEmptyFavorite() {
        // Arrange
        radio.prenderRadio();

        // Act
        String result = service.usarEstacion(0);

        // Assert
        assertEquals("Favorito vacío", result, "Should return Favorito vacío message");
    }

    @Test
    public void testTieneFavoritoWhenEmpty() {
        // Act
        boolean result = service.tieneFavorito(0);

        // Assert
        assertFalse(result, "Should not have favorite");
    }

    @Test
    public void testTieneFavoritoWhenSaved() {
        // Arrange
        radio.guardarEstacion(0);

        // Act
        boolean result = service.tieneFavorito(0);

        // Assert
        assertTrue(result, "Should have favorite");
    }
}
