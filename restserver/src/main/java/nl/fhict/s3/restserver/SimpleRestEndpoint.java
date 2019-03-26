package nl.fhict.s3.restserver;

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

@Path("/greeting")
public class SimpleRestEndpoint {

    private static final Logger log = LoggerFactory.getLogger(SimpleRestEndpoint.class);
    private static GreetingStore greetingStore = GreetingStore.getInstance();
    private final Gson gson;

    public SimpleRestEndpoint() {
        gson = new Gson();
    }

    // region GET
    @Path("/{greeting}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getGreeting(@PathParam("greeting") String greeting) {
        log.info("GET greeting called for key: {}", greeting);
        Greeting myResponse = greetingStore.getGreeting(greeting);

        return Response.status(200).entity(gson.toJson(myResponse)).build();
    }

    // region GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAllGreetings() {
        log.info("GET all called");

        return Response.status(200).entity(gson.toJson(greetingStore.getAll())).build();
    }
    // endregion

    // region POST
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGreeting(Greeting greeting) {
        log.info("POST add called for key: {}", greeting.getName());

        greetingStore.addGreeting(greeting);

        return Response.status(200).entity(gson.toJson(greeting)).build();
    }
    // endregion
}
