package Task3;

public final class Airplane extends AirTransport {
    private double altitude;
    private final double maxAltitude;

    public Airplane(String model, double maxAltitude) {
        super(model);
        this.maxAltitude = maxAltitude;
        this.altitude = 0;
    }

    @Override
    public void start() {
        isFlying = true;
        System.out.println("Самолет " + model + " готов к взлету");
    }

    @Override
    public void stop() {
        isFlying = false;
        altitude = 0;
        System.out.println("Самолет " + model + " приземлился");
    }

    public void takeOff() {
        if (isFlying) {
            altitude = 10000;
            System.out.println("Взлетели на высоту " + altitude + " метров");
        } else {
            System.out.println("Сначала подготовьте самолет к полету!");
        }
    }

    public void land() {
        altitude = 0;
        System.out.println(" Идем на посадку...");
    }

    @Override
    public void displayInfo() {
        System.out.println("=== САМОЛЕТ ===");
        System.out.println("Модель: " + model);
        System.out.println("Макс. высота: " + maxAltitude + " метров");
        System.out.println("Состояние: " + (isFlying ? "в полете" : "на земле"));
        System.out.println("Высота: " + altitude + " метров");
        System.out.println("Топливо: " + getFuelType().getName());
    }

    @Override
    public FuelType getFuelType() {
        return FuelType.KEROSENE;
    }

    @Override
    public double getMaxAltitude() {
        return maxAltitude;
    }
}