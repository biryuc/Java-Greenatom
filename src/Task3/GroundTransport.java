package Task3;

public abstract sealed class GroundTransport implements Vehicle
        permits Car, Bicycle {

    protected final String model;
    protected boolean isRunning;

    public GroundTransport(String model) {
        this.model = model;
        this.isRunning = false;
    }

    @Override
    public String getModel() {
        return model;
    }

    public abstract int getWheelCount();
}