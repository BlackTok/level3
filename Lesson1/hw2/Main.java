package Lesson1.hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Object[] array= new Object[3];

        array[0] = new String("Строка");
        array[1] = new Integer(555);
        array[2] = new Lesson1.hw2.Main();

        transform(array);
    }

    public static <T> void transform(T[] array) {
        List<T> arrayList = new ArrayList<>();

        arrayList.addAll(Arrays.asList(array));

        for (T t : arrayList) {
            System.out.println(t);
        }
    }
}
