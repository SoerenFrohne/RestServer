package restful;

import java.util.ArrayList;

public class TestSeries {

    private int id;
    private int timeInterval;
    private String consumer;
    private String measurand;
    private ArrayList<Measurement> measurements;

    public TestSeries() {
    }

    public TestSeries(int id, int timeInterval,
                      String consumer, String measurand) {
        super();
        this.id = id;
        this.timeInterval = timeInterval;
        this.consumer = consumer;
        this.measurand = measurand;
    }

    public TestSeries(int id, int timeInterval, String consumer, String measurand, ArrayList<Measurement> measurements) {
        this.id = id;
        this.timeInterval = timeInterval;
        this.consumer = consumer;
        this.measurand = measurand;
        this.measurements = measurements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getMeasurand() {
        return measurand;
    }

    public void setMeasurand(String measurand) {
        this.measurand = measurand;
    }

    public ArrayList<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(ArrayList<Measurement> measurements) {
        this.measurements = measurements;
    }
}
