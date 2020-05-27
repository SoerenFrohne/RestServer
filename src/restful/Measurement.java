package restful;

public class Measurement {
    private int incrementNumber;
    private double value;

    public Measurement() {
    }

    public Measurement(int incrementNumber, double value) {
        this.incrementNumber = incrementNumber;
        this.value = value;
    }

    public int getIncrementNumber() {
        return incrementNumber;
    }

    public void setIncrementNumber(int incrementNumber) {
        this.incrementNumber = incrementNumber;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
