package lesson6.hw1;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.*;

public class Main {
    private static final Logger LOG = Logger.getLogger(Car.class.getName());

    public static final int CARS_COUNT = 4;
    public static final int CARS_COUNT_IN_TUNNEL = CARS_COUNT / 2;
    private static final Semaphore START_LINE = new Semaphore(CARS_COUNT);
    private static final Semaphore FINISH_LINE = new Semaphore(CARS_COUNT, true);

    public static void main(String[] args) {
        addLogHandler();

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        LOG.log(Level.INFO, "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60, CARS_COUNT, LOG), new Tunnel(80, CARS_COUNT_IN_TUNNEL, LOG), new Road(40, CARS_COUNT, LOG));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), START_LINE, FINISH_LINE, LOG);
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
        LOG.log(Level.INFO, "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        while (true) { // ждем пока все машины пересекут финишную линию
            if (FINISH_LINE.availablePermits() <= 0) {
                break;
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        LOG.log(Level.INFO, "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    private static void addLogHandler () {
        try {
            Handler handler = new FileHandler("C://logs//logs.log");
            handler.setFormatter(new SimpleFormatter());

            LOG.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
