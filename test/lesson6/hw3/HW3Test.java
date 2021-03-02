package test.lesson6.hw3;

import lesson6.hw3.HW3;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class HW3Test {
    private final int[] array;
    //private final boolean resultTest;

    public HW3Test(int[] array) {
        this.array = array;
        //this.resultTest = resultTest;

        //System.out.println(this.resultTest);
    }

    @Parameterized.Parameters
    public static Collection getArrays() {
        return Arrays.asList(new int[][] {
                {0,1,2,3,4,5,6,7,8,9}, //true
                {4,5,4,5,4,5}, //true
                {3,9,8,4,6,2,1,4,5,6,7,0,2,1}, //true
                {0,2,3,5,6,7,8,9} //false
        });
    }

    @Test
    public void getArrayTest() {
        Assert.assertTrue(HW3.checkArray(array));
    }
}
