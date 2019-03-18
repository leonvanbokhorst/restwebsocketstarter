package nl.fontys.s3;

import nl.fontys.s3.handler.simpleHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Hello world!
 *
 */
public class JettyServer
{
    public static void main( String[] args ) throws Exception {
        Server server = new Server(8080);

        // handler
        ServletHandler handler = new ServletHandler();

        // mapping
        handler.addServletWithMapping(simpleHandler.class, "/hello");
        server.setHandler(handler);

        server.start();
        server.join();
    }
}
