package restful;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import restful.db.DatabaseService;

import java.sql.SQLException;
import java.util.Arrays;

public enum DatabaseUtility {

    // Singleton instance
    INSTANCE;

    private final ObservableList<TestSeries> currentSeries;
    private final DatabaseService databaseService = new DatabaseService();

    DatabaseUtility() {
        currentSeries = FXCollections.observableArrayList();
    }

    public ObservableList<TestSeries> getCurrentSeries() {
        return currentSeries;
    }

    //Ready
    public Measurement[] readMeasurementsFromDatabase(int seriesId) throws ClassNotFoundException, SQLException {
        Measurement[] measurements;
        this.databaseService.connectDb();
        measurements = this.databaseService.readMeasurements(seriesId).toArray(new Measurement[0]);
        this.databaseService.closeDb();
        return measurements;
    }

    //Ready
    public void speichereMessungInDb(int messreihenId, Measurement measurement) throws ClassNotFoundException, SQLException {
        this.databaseService.connectDb();
        this.databaseService.InsertMeasurement(messreihenId, measurement);
        this.databaseService.closeDb();
    }

    //Ready
    public void readTestSeriesFromDatabaseInclusiveMeasurements() throws ClassNotFoundException, SQLException {
        this.databaseService.connectDb();
        TestSeries[] series = this.databaseService.readTestSeriesInclusiveMeasurements();
        this.databaseService.closeDb();
        int size = series.length;
        System.out.println("Read from database with size: " + Arrays.toString(series));
        if (size > 0) {
            this.currentSeries.clear();
            this.currentSeries.addAll(series);
        }
    }

    public void saveTestSeriesToDatabase(TestSeries testSeries) throws ClassNotFoundException, SQLException {
        this.databaseService.connectDb();
        this.databaseService.InsertTestSeries(testSeries);
        this.databaseService.closeDb();
        this.currentSeries.add(testSeries);
    }

    public String getDaten() {
        return "in getDaten";
    }
}
