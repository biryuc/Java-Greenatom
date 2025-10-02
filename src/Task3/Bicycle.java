package Task3;

public final class Bicycle extends GroundTransport {
    private int speed;
    private final int wheelCount;

    public Bicycle(String model, int wheelCount) {
        super(model);
        this.wheelCount = wheelCount;
        this.speed = 0;
    }

    @Override
    public void start() {
        isRunning = true;
        System.out.println("Велосипед " + model + " готов к поездке");
    }

    @Override
    public void stop() {
        isRunning = false;
        speed = 0;
        System.out.println("Велосипед " + model + " остановлен");
    }

    public void pedal(int newSpeed) {
        if (isRunning) {
            speed = newSpeed;
            System.out.println("Крутим педали со скоростью " + speed + " км/ч");
        } else {
            System.out.println("Сначала начните движение!");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("=== ВЕЛОСИПЕД ===");
        System.out.println("Модель: " + model);
        System.out.println("Колес: " + wheelCount);
        System.out.println("Состояние: " + (isRunning ? "в движении" : "остановлен"));
        System.out.println("Скорость: " + speed + " км/ч");
        System.out.println("Топливо: " + getFuelType().getName());
    }

    @Override
    public FuelType getFuelType() {
        return FuelType.HUMAN_POWER;
    }

    @Override
    public int getWheelCount() {
        return wheelCount;
    }
}