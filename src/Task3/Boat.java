package Task3;

public final class Boat extends WaterTransport {
    private double speed;
    private final double displacement;

    public Boat(String model, double displacement) {
        super(model);
        this.displacement = displacement;
        this.speed = 0;
    }

    @Override
    public void start() {
        isSailing = true;
        System.out.println("Корабль " + model + " вышел в море");
    }

    @Override
    public void stop() {
        isSailing = false;
        speed = 0;
        System.out.println("Корабль " + model + " пришвартован");
    }

    public void sail(double newSpeed) {
        if (isSailing) {
            speed = newSpeed;
            System.out.println("Плывем со скоростью " + speed + " узлов");
        } else {
            System.out.println("Сначала выйдите в море!");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("=== КОРАБЛЬ ===");
        System.out.println("Модель: " + model);
        System.out.println("Водоизмещение: " + displacement + " тонн");
        System.out.println("Состояние: " + (isSailing ? "в плавании" : "в порту"));
        System.out.println("Скорость: " + speed + " узлов");
        System.out.println("Топливо: " + getFuelType().getName());
    }

    @Override
    public FuelType getFuelType() {
        return FuelType.DIESEL;
    }

    @Override
    public double getDisplacement() {
        return displacement;
    }
}