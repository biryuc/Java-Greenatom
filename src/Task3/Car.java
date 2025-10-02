package Task3;

public final class Car extends GroundTransport {
    private int speed;
    private final int wheelCount;

    public Car(String model, int wheelCount) {
        super(model);
        this.wheelCount = wheelCount;
        this.speed = 0;
    }

    @Override
    public void start() {
        isRunning = true;
        System.out.println(" Автомобиль " + model + " заведен");
    }

    @Override
    public void stop() {
        isRunning = false;
        speed = 0;
        System.out.println("Автомобиль " + model + " остановлен");
    }

    public void accelerate(int newSpeed) {
        if (isRunning) {
            speed = newSpeed;
            System.out.println("Едем со скоростью " + speed + " км/ч");
        } else {
            System.out.println("Сначала заведите автомобиль!");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("=== АВТОМОБИЛЬ ===");
        System.out.println("Модель: " + model);
        System.out.println("Колес: " + wheelCount);
        System.out.println("Состояние: " + (isRunning ? "заведен" : "заглушен"));
        System.out.println("Скорость: " + speed + " км/ч");
        System.out.println("Топливо: " + getFuelType().getName());
    }

    @Override
    public FuelType getFuelType() {
        return FuelType.GASOLINE;
    }

    @Override
    public int getWheelCount() {
        return wheelCount;
    }
}
