package nl.fhict.s3;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/simple")
public class SimpleRestEndpoint {

    private static final Logger log = LoggerFactory.getLogger(SimpleRestEndpoint.class);
    private final Gson gson;

    public SimpleRestEndpoint() {
        gson = new Gson();
    }

    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response Hello() {
        log.info("Hello called");
        String myResponse = "Hello from " + SimpleRestEndpoint.class.getName();

        return Response.status(200).entity(gson.toJson(myResponse)).build();
    }

    @Path("hello/{greeting}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response Hello(@PathParam("greeting") String greeting) {
        log.info("Hello {greeting} called");
        String myResponse = "Hello " + greeting;

        return Response.status(200).entity(gson.toJson(myResponse)).build();
    }

    @Path("hello/pojo/{greeting}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response HelloPojo(@PathParam("greeting") String greeting) {
        log.info("Hello POJO called");
        Greeting myResponse = new Greeting("Hello " + greeting + " POJO", 50);

        return Response.status(200).entity(gson.toJson(myResponse)).build();
    }

    // POST
    @POST
    @Path("greeting")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGreeting(Greeting greeting) {
        log.info("Greeting POST called");
        Greeting myResponse = new Greeting("Hello POST greeting " + greeting.getName(), 11111111);

        return Response.status(200).entity(gson.toJson(myResponse)).build();
    }
}
