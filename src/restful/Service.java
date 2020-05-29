package restful;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import restful.db.DatabaseService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/service")
public class Service {

    private final ObjectMapper mapper = new ObjectMapper();
    private final DatabaseService databaseService = new DatabaseService();

    @GET
    @Path("/api-status")
    @Produces(MediaType.TEXT_HTML)
    public String getApiStatus() {
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

    @POST // Post method for creating new measurement, put for updating
    @Path("/testseries/{seriesId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMeasurement(@PathParam("seriesId") Integer id, String measurement) {
        System.out.println("postMeasurement");

        try {
            Measurement m = mapper.readValue(measurement, Measurement.class);

            System.out.println("Received Meadurement: " + m.toString());

            this.databaseService.connectDb();
            this.databaseService.InsertMeasurement(id, m);
            this.databaseService.closeDb();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.out.println("Couldn't read Measurement, received: " + measurement);
            return Response.status(Response.Status.CONFLICT).entity(e).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/testseries")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTestseries(String testSeries) {
        System.out.println("postMeasurement");

        try {
            TestSeries t = mapper.readValue(testSeries, TestSeries.class);

            System.out.println("Received Testseries: " + t.toString());

            this.databaseService.connectDb();
            this.databaseService.InsertTestSeries(t);
            this.databaseService.closeDb();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.out.println("Couldn't read Testseries");
            return Response.status(Response.Status.CONFLICT).entity(e).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }
}
