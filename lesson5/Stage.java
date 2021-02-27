package lesson5;

public abstract class Stage {
    protected int length, carsCountInStage;
    protected String description;
    public String getDescription() {
        return description;
    }

    public int getCarsCountInStage() {
        return carsCountInStage;
    }

    public abstract void go(Car c);
}
