package test.lesson6.hw2;

import lesson6.hw2.HW2;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class HW2Test {
    private final int[] array1;
    private final int[] array2;

    public HW2Test(int[] array1, int[] array2) {
        this.array1 = array1;
        this.array2 = array2;
    }

    @Parameterized.Parameters
    public static Collection getArrays() {
        return Arrays.asList(new int[][][] {
                {{0,1,2,3,4,5,6,7,8,9}, {5,6,7,8,9}},
                {{4,5,4,5,4,5}, {5}},
                {{3,9,8,4,6,2,1,4,5,6,7,0,2,1}, {5,6,7,0,2,1}},
                {{4}, {}}
        });
    }

    @Test
    public void getArrayTest() {
        Assert.assertArrayEquals(array2, HW2.getArray(array1));
    }
}
