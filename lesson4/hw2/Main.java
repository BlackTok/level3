package lesson4.hw2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static ExecutorService executorService;

    public static void main(String[] args) {
        executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new MyClassThread());

        executorService.shutdown();
    }
}
