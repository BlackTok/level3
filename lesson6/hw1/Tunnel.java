package lesson6.hw1;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore tunnel;

    public Tunnel(int length, int carsCountInStage) {
        this.length = length;
        this.carsCountInStage = carsCountInStage;
        this.description = "Тоннель " + length + " метров";
        this.tunnel = new Semaphore(carsCountInStage);
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
