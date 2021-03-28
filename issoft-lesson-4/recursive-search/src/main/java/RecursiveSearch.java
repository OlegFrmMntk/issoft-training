import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecursiveSearch {

    private static final Logger logger = LoggerFactory.getLogger(RecursiveSearch.class);

    private RecursiveSearch() {
    }

    private static int binarySearch0(int[] numbers, int left, int right, int key) {

        if (right - left <= 1) {

            int answer = -1;
            if (numbers[left] == key) {
                answer = left;
            }
            else if (numbers[right] == key) {
                answer = right;
            }

            logger.debug(String.format("KEY = %d; ANSWER = %d.", key, answer));

            return answer;
        }

        int mid = (left + right) >>> 1;

        logger.debug(String.format("LEFT = %d; MID = %d; RIGHT = %d; KEY = %d.", left, mid, right, key));

        if (numbers[mid] >= key) {
            return binarySearch0(numbers, left, mid, key);
        }
        else {
            return binarySearch0(numbers, mid, right, key);
        }
    }

    public static int binarySearch(int[] numbers, int key) {

        if (numbers.length > 0) {
            return binarySearch0(numbers, 0, numbers.length - 1, key);
        }
        else {
            return -1;
        }
    }

}
