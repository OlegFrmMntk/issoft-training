import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter array length: ");
        int size = input.nextInt();

        int[] numbers = new int[size];

        System.out.println("Enter array elements:");
        for (int i = 0; i < size; i++) {
            numbers[i] = input.nextInt();
        }


        System.out.println("Enter the number you want to find: ");
        int number = input.nextInt();

        while (number != -1) {
            System.out.printf("Number position: %d%n", RecursiveSearch.binarySearch(numbers, number));

            System.out.println("Enter the number you want to find: ");
            number = input.nextInt();
        }

    }
}