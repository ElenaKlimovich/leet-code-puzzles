package medium;

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
//        System.out.println(Arrays.deepToString(generateMatrix(4)));

        minimumDeleteSum("delete", "leet"); //403 ; let
    }

    // https://leetcode.com/problems/combinations
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        createCombinations(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void createCombinations(int start, int n, int k, List<Integer> current, List<List<Integer>>result) {
        if(k == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for(int i=start; i<=n; i++) {
            current.add(i);
            createCombinations(i+1, n, k-1, current, result);
            current.remove(current.size()-1);
        }
    }

    // https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
    public static int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i<= n1; i++)
            dp[i][0] = dp[i - 1][0] + s1.charAt(i-1);

        for (int j = 1; j<= n2; j++)
            dp[0][j] = dp[0][j - 1] + s2.charAt(j-1);

        for (int i = 1; i<= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int a = dp[i-1][j] + s1.charAt(i-1);
                    int b = dp[i][j-1] + s2.charAt(j-1);
                    dp[i][j] = Math.min(a, b);
                }
            }
        }

        return dp[n1][n2];
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
