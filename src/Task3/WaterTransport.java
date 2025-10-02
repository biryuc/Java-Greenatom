package Task3;

public abstract sealed class WaterTransport implements Vehicle
        permits Boat {

    protected final String model;
    protected boolean isSailing;

    public WaterTransport(String model) {
        this.model = model;
        this.isSailing = false;
    }

    @Override
    public String getModel() {
        return model;
    }

    public abstract double getDisplacement();
}