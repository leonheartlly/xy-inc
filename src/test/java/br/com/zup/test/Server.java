package br.com.zup.test;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Servidor para testes
 * 
 * @author Lucas
 *
 */
public class Server {
	
    public static void main(String[] args) throws IOException {

        HttpServer server = startServer();

        System.out.println("Servidor Rodando");

        // pressione Enter para finalizar o servidor
        System.in.read();
        server.shutdownNow();
    }

    /**
     * Inicia o servidor de teste.
     * 
     * @return
     */
    protected static HttpServer startServer() {
        ResourceConfig config = new ResourceConfig().packages("br.com.zup");

        URI uri = URI.create("http://localhost:8080/");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        return server;
    }
}
