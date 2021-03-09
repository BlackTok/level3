package lesson7;

import lesson7.annotations.AfterSuite;
import lesson7.annotations.BeforeSuite;
import lesson7.annotations.Test;

public class Main {
    public static void main(String[] args) throws Exception {
        lesson7.Test.start(new Main());
    }

    @Test
    public static int sum(int a, int b) {
        return a + b;
    }

    @Test
    public static String sumString(String a, String b) {
        return a + b;
    }

    @Test(priority = Priority.FIVE)
    public static int multiply(int a, int b) {
        return a * b;
    }

    @Test(priority = Priority.ONE)
    public static int subtract(int a, int b) {
        return a - b;
    }

    @BeforeSuite
    public static void before() {
        System.out.println("START TEST");
    }

    @AfterSuite
    public static void after() {
        System.out.println("END TEST");
    }
}
