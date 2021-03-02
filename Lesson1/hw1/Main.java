package Lesson1.hw1;

public class Main {
    public static void main(String[] args) {
        Object[] array = new Object[3];

        array[0] = new String("Строка");
        array[1] = new Integer(555);
        array[2] = new Main();

        replace(array, 0, 2);
    }

    public static <T> void replace(T[] array, int index1, int index2) {
        T element1 = array[index1];
        array[index1] = array[index2];
        array[index2] = element1;

        for (T e : array) {
            System.out.println(e);
        }
    }
}
