package server;

import com.sun.net.httpserver.HttpServer;
import controller.RadioController;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class RadioServer {

    public static void main(String[] args) throws IOException {
        RadioController controller = new RadioController();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/encender", exchange -> {
            String response = controller.obtenerEstacion();
            controller.encender();
            send(exchange, controller.obtenerEstacion());
        });

        server.createContext("/apagar", exchange -> {
            controller.apagar();
            send(exchange, "APAGADA");
        });

        server.createContext("/avanzar", exchange -> {
            controller.adelantarEstacion();
            send(exchange, controller.obtenerEstacion());
        });

        server.createContext("/cambiar", exchange -> {
    send(exchange, controller.toggleBanda());
});


        server.createContext("/favorito", exchange -> {
            String query = exchange.getRequestURI().getQuery(); // pos=3
            int pos = Integer.parseInt(query.split("=")[1]);
            String response = controller.usarEstacion(pos);
            send(exchange, response);
        });

        server.start();
        System.out.println("Servidor iniciado en http://localhost:8000");
    }

    private static void send(com.sun.net.httpserver.HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
