package lesson6.hw1;

import java.io.IOException;
import java.util.logging.*;

public class Road extends Stage {
    private final Logger log;

    public Road(int length, int carsCountInStage, Logger log) {
        this.length = length;
        this.carsCountInStage = carsCountInStage;
        this.description = "Дорога " + length + " метров";
        this.log = log;

        log.log(Level.INFO, String.format("Добавилось: %s", description));
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            log.log(Level.SEVERE, e.getLocalizedMessage());
        }
    }
}
