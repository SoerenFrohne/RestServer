package restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/service")
public class Service {

    @GET
    @Path("/api-status")
    @Produces(MediaType.TEXT_HTML)
    public String returnTitle(){
        return "<p>Service available!</p>";
    }
}
