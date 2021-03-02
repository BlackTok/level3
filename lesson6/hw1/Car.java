package lesson6.hw1;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static Car winner;
    private final Semaphore startLine, finishLine;

    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, Semaphore startLine, Semaphore finishLine) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.startLine = startLine;
        this.finishLine = finishLine;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");


            startLine.acquire();

            while (true) {
                if (startLine.availablePermits() <= 0) {
                    startLine.release();
                    break;
                }
            }

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            System.out.println(this.name + " пришел к финишу!");
            if (winner == null) {
                winner = this;
                System.out.println(this.name + " WIN!");
            }

            finishLine.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
