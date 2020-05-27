package restful;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import restful.db.DatabaseService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/service")
public class Service {

    private ObjectMapper mapper = new ObjectMapper();
    private DatabaseService databaseService = new DatabaseService();

    @GET
    @Path("/api-status")
    @Produces(MediaType.TEXT_HTML)
    public String getApiStatus(){
        return "<p>Service available!</p>";
    }

    @GET
    @Path("/testseries")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getTestseries() {
        try {
            this.databaseService.connectDb();
            TestSeries[] series = this.databaseService.readTestSeriesInclusiveMeasurements();
            this.databaseService.closeDb();
            return this.mapper.writeValueAsString(series);
        } catch (ClassNotFoundException | SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/testseries/{seriesId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getTestseries(@PathParam("seriesId") Integer id) {
        try {
            this.databaseService.connectDb();
            ArrayList<Measurement> series = this.databaseService.readMeasurements(id);
            this.databaseService.closeDb();
            return this.mapper.writeValueAsString(series);
        } catch (ClassNotFoundException | SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
