
package Practical_2;

import java.util.*;

public class Testing_All {

    // Linear Search method
    public static int linearSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search method
    public static int binarySearch(int[] array, int key) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == key) {
                return mid;
            }
            if (array[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.print("Enter the key element to search: ");
        int key = scanner.nextInt();

        // Linear Search timing
        long startTime = System.nanoTime();
        int linearResult = linearSearch(array, key);
        long endTime = System.nanoTime();
        long linearTime = endTime - startTime;

        // Binary Search (requires sorted array)
        Arrays.sort(array);
        startTime = System.nanoTime();
        int binaryResult = binarySearch(array, key);
        endTime = System.nanoTime();
        long binaryTime = endTime - startTime;

        // Display results
        System.out.println("\nLinear Search:");
        if (linearResult == -1) {
            System.out.println("Element not found.");
        } else {
            System.out.println("Element found at index: " + linearResult);
        }
        System.out.println("Time taken by Linear Search: " + linearTime + " nanoseconds");

        System.out.println("\nBinary Search (after sorting):");
        if (binaryResult == -1) {
            System.out.println("Element not found.");
        } else {
            System.out.println("Element found at index: " + binaryResult);
        }
        System.out.println("Time taken by Binary Search: " + binaryTime + " nanoseconds");

        scanner.close();
    }
}
