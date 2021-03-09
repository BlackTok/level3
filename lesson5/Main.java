package lesson5;

import java.util.concurrent.Semaphore;

public class Main {
    public static final int CARS_COUNT = 4;
    public static final int CARS_COUNT_IN_TUNNEL = CARS_COUNT / 2;
    private static final Semaphore START_LINE = new Semaphore(CARS_COUNT);
    private static final Semaphore FINISH_LINE = new Semaphore(CARS_COUNT, true);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60, CARS_COUNT), new Tunnel(80, CARS_COUNT_IN_TUNNEL), new Road(40, CARS_COUNT));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), START_LINE, FINISH_LINE);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        while (true) { // ждем пока машины займут все места на старте
            if (START_LINE.availablePermits() <= 0) {
                START_LINE.release(CARS_COUNT);
                break;
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        while (true) { // ждем пока все машины пересекут финишную линию
            if (FINISH_LINE.availablePermits() <= 0) {
                break;
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
