import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeetCodeMedium4 {
    public static void main(String[] args) {
//        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3));

//        System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})); // [1,2,3,6,9,8,7,4,5]
//        System.out.println(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}})); // [1,2,3,4,8,12,11,10,9,5,6,7]
//        System.out.println(Arrays.deepToString(generateMatrix(3))); // [[1,2,3],[8,9,4],[7,6,5]]
        System.out.println(Arrays.deepToString(generateMatrix(4)));
    }

    // https://leetcode.com/problems/spiral-matrix-ii/
    public static int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        List<Integer> numbers = IntStream.rangeClosed(1, n * n).boxed().collect(Collectors.toList());

        int left = 0;
        int top = 0;
        int right = n - 1;
        int bottom = n - 1;
        int count = 1;

        while (left <= right && top <= bottom) {

            for (int i = left; i <= right; i++, count++) {
                matrix[top][i] = count;
            }
            top++;

            for (int j = top; j <= bottom; j++, count++) {
                matrix[j][right] = count;
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--, count++) {
                    matrix[bottom][i]=count;
                }
                bottom--;
            }

            if (left <= right) {
                for (int j = bottom; j >= top; j--, count++) {
                    matrix[j][left] = count;
                }
                left++;
            }
        }

        return matrix;
    }

    // https://leetcode.com/problems/spiral-matrix/
    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> spiral = new ArrayList<>();

        int lines = matrix.length, columns = matrix[0].length;
        int left = 0;
        int top = 0;
        int right = columns - 1;
        int bottom = lines - 1;

        while (left <= right && top <= bottom) {

            for (int i = left; i <= right; i++) {
                spiral.add(matrix[top][i]);
            }
            top++;

            for (int j = top; j <= bottom; j++) {
                spiral.add(matrix[j][right]);
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    spiral.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (left <= right) {
                for (int j = bottom; j >= top; j--) {
                    spiral.add(matrix[j][left]);
                }
                left++;
            }
        }

        return spiral;
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> result = new ArrayList<>();

        int leftPointer = 0;
        int rightPointer = arr.length - 1;

        while (rightPointer - leftPointer >= k) {
            int leftDiff = Math.abs(arr[leftPointer] - x);
            int rightDiff = Math.abs(arr[rightPointer] - x);

            if (leftDiff <= rightDiff)
                rightPointer--;
            else
                leftPointer++;
        }

        while (leftPointer <= rightPointer) {
            result.add(arr[leftPointer]);
            leftPointer++;
        }

        return result;
    }
}
