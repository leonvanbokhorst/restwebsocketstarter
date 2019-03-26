package nl.fhict.s3.restserver;

import nl.fhict.s3.restserver.endpoint.SimpleRestEndpoint;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class RestServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        server.setHandler(getJerseyHandler());

        server.start();
        server.join();
    }

    private static Handler getJerseyHandler() {
        ServletContextHandler handler = new ServletContextHandler(
            ServletContextHandler.SESSIONS);

        handler.setContextPath("/");

        ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter("jersey.config.server.provider.classnames",
            SimpleRestEndpoint.class.getCanonicalName());

        return handler;
    }
}
