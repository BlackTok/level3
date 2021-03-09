package lesson6.hw2;

public class HW2 {
    public static int[] getArray(int[] arrayFirst) throws RuntimeException {
        if (arrayFirst == null)
            throw new RuntimeException("Array is equal null");

        int indexLust4 = -1;
        for (int i = 0; i < arrayFirst.length; i++) {
            if (arrayFirst[i] == 4) {
                indexLust4 = i;
            }
        }

        if (indexLust4 < 0)
            throw new RuntimeException("Array does not contain 4");

        int countElement = arrayFirst.length - indexLust4 - 1;
        int[] arraySecond = new int[countElement];

        for (int i = 0; i < countElement; i++) {
            arraySecond[i] = arrayFirst[i + indexLust4 + 1];
        }

        return arraySecond;
    }
}
