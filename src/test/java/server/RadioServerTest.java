package server;

import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RadioServerTest {

    private static Thread serverThread;

    @BeforeAll
    static void startServer() {
        serverThread = new Thread(() -> {
            try {
                RadioServer.main(new String[]{});
            } catch (Exception e) {
                fail("No se pudo iniciar el servidor");
            }
        });
        serverThread.setDaemon(true); // no bloquea los tests
        serverThread.start();

        // Esperar a que el servidor levante
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
    }

    private String call(String endpoint) throws Exception {
        URL url = new URL("http://localhost:8000" + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(200, conn.getResponseCode());

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = reader.readLine();
        reader.close();

        return response;
    }

    @Test
    @Order(1)
    void testEncender() throws Exception {
        String response = call("/encender");
        assertNotNull(response);
        assertTrue(
            response.contains("FM") || response.contains("AM"),
            "Debe responder con una estación válida"
        );
    }

    @Test
    @Order(2)
    void testAvanzar() throws Exception {
        String antes = call("/encender");
        String despues = call("/avanzar");

        assertNotEquals(antes, despues, "La estación debería cambiar");
    }

    @Test
    @Order(3)
    void testCambiarBanda() throws Exception {
        String antes = call("/encender");
        String despues = call("/cambiar");

        assertNotEquals(
            antes.contains("FM"),
            despues.contains("FM"),
            "La banda debería cambiar"
        );
    }

    @Test
    @Order(4)
    void testGuardarFavorito() throws Exception {
        String response = call("/favorito?pos=1");
        assertTrue(
            response.equals("GUARDADO") ||
            response.contains("FM") ||
            response.contains("AM")
        );
    }

    @Test
    @Order(5)
    void testApagar() throws Exception {
        String response = call("/apagar");
        assertEquals("APAGADA", response);
    }
}
