package lesson6.hw1;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.*;

public class Tunnel extends Stage {
    private final Logger log;
    private final Semaphore tunnel;

    public Tunnel(int length, int carsCountInStage, Logger log) {
        this.length = length;
        this.carsCountInStage = carsCountInStage;
        this.description = "Тоннель " + length + " метров";
        this.tunnel = new Semaphore(carsCountInStage);
        this.log = log;

        log.log(Level.INFO, String.format("Добавилось: %s", description));
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                tunnel.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                tunnel.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
