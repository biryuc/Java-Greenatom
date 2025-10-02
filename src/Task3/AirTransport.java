package Task3;

public abstract sealed class AirTransport implements Vehicle
        permits Airplane {

    protected final String model;
    protected boolean isFlying;

    public AirTransport(String model) {
        this.model = model;
        this.isFlying = false;
    }

    @Override
    public String getModel() {
        return model;
    }

    public abstract double getMaxAltitude();
}