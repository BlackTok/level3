package lesson6.hw3;

import java.util.Arrays;

public class HW3 {
    public static boolean checkArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1 || array[i] == 4)
                return true;
        }

        return false;
    }
}
