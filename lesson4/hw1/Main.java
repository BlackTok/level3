package lesson4.hw1;

public class Main {
    private static final Object monitor = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("A");

                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    monitor.notify();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("B");

                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    monitor.notify();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("C");

                    monitor.notify();

                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        }).start();
    }
}
