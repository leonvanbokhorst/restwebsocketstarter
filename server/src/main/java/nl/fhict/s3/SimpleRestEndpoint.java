package nl.fhict.s3;

import com.google.gson.Gson;
import java.util.HashMap;
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
    private static GreetingStore greetingStore = new GreetingStore(new HashMap<>());
    private final Gson gson;

    public SimpleRestEndpoint() {
        gson = new Gson();
    }

    // region GET
    @Path("/hello/{greeting}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response HelloPojo(@PathParam("greeting") String greeting) {
        log.info("GET hello called");
        Greeting myResponse = new Greeting("Hello from GET " + greeting, 50);

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
        log.info("POST add called");

        greetingStore.addGreeting(greeting);
        Greeting myResponse = new Greeting("Hello from POST " + greeting.getName(),
            greeting.getAge());

        return Response.status(200).entity(gson.toJson(myResponse)).build();
    }
    // endregion
}
