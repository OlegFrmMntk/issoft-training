import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class RecursiveSearchTest {

    @Test
    public void binarySearch() {

        int[] numbers = new int[1_000_000];

        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt();
        }

        Arrays.sort(numbers);

        final int TEST_NUMBER = 100_000;

        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();

        for (int i = 0; i < TEST_NUMBER; i++) {
            int findNumber = random.nextInt();

            int expectedNumber = -1;
            for (int j = 0; j < numbers.length; j++) {
                if (findNumber == numbers[j]) {
                    expectedNumber =  j;
                    break;
                }
            }

            int actualNumber = RecursiveSearch.binarySearch(numbers, findNumber);

            expected.add(expectedNumber);
            actual.add(actualNumber);
        }

        Assert.assertEquals(expected, actual);
    }
}