package Task3;

public enum FuelType {
    GASOLINE("Бензин"),
    DIESEL("Дизель"),
    ELECTRICITY("Электричество"),
    HUMAN_POWER("Мускульная сила"),
    KEROSENE("Керосин");

    private final String name;

    FuelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}