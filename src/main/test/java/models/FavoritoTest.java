package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FavoritoTest {

    @Test
    public void testFavoritoConstructorAndGetters() {
        // Arrange
        int tipo = 1;
        double estacion = 530.0;

        // Act
        Favorito favorito = new Favorito(tipo, estacion);

        // Assert
        assertEquals(tipo, favorito.getTipo());
        assertEquals(estacion, favorito.getEstacion());
    }

    @Test
    public void testFavoritoWithFMTipo() {
        // Arrange
        int tipoFM = 0;
        double estacionFM = 98.5;

        // Act
        Favorito favorito = new Favorito(tipoFM, estacionFM);

        // Assert
        assertEquals(0, favorito.getTipo());
        assertEquals(98.5, favorito.getEstacion());
    }

    @Test
    public void testFavoritoWithAMTipo() {
        // Arrange
        int tipoAM = 1;
        double estacionAM = 1200.0;

        // Act
        Favorito favorito = new Favorito(tipoAM, estacionAM);

        // Assert
        assertEquals(1, favorito.getTipo());
        assertEquals(1200.0, favorito.getEstacion());
    }

    @Test
    public void testFavoritoWithMinimumFMFrequency() {
        // Arrange
        int tipo = 0;
        double estacion = 87.9;

        // Act
        Favorito favorito = new Favorito(tipo, estacion);

        // Assert
        assertEquals(87.9, favorito.getEstacion());
    }

    @Test
    public void testFavoritoWithMaximumFMFrequency() {
        // Arrange
        int tipo = 0;
        double estacion = 107.9;

        // Act
        Favorito favorito = new Favorito(tipo, estacion);

        // Assert
        assertEquals(107.9, favorito.getEstacion());
    }

    @Test
    public void testFavoritoWithMinimumAMFrequency() {
        // Arrange
        int tipo = 1;
        double estacion = 530.0;

        // Act
        Favorito favorito = new Favorito(tipo, estacion);

        // Assert
        assertEquals(530.0, favorito.getEstacion());
    }

    @Test
    public void testFavoritoWithMaximumAMFrequency() {
        // Arrange
        int tipo = 1;
        double estacion = 1610.0;

        // Act
        Favorito favorito = new Favorito(tipo, estacion);

        // Assert
        assertEquals(1610.0, favorito.getEstacion());
    }
}
