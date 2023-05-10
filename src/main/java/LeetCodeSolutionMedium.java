import java.util.*;

public class LeetCodeSolutionMedium {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(sumEvenAfterQueries(new int[]{1, 2, 3, 4}, new int[][]{{1, 0}, {-3, 1}, {-4, 0}, {2, 3}})));
//        System.out.println(Arrays.toString(sumEvenAfterQueries(new int[]{1}, new int[][]{{4, 0}})));
//        sumEvenAfterQueries(new int[]{1, 2, 3, 4}, new int[][]{{1, 0}, {-3, 1}, {-4, 0}, {2, 3}});
        Overloading1 ol = new Overloading1();
        ol.sum(8.7, 2.0); // Primitive
        ol.sum((Double) 8.7, (Double)2.0); // Wrapper
        ol.sum(8.7, null); // Wrapper
    }


    static class Overloading1 {
        void sum(double d1, double d2) {
            System.out.println(("Primitive"));
        }

        void sum(Double d1, Double d2) {
            System.out.println(("Wrapper"));
        }

    }

/*
You are given an integer array nums and an array queries where queries[i] = [vali, indexi].

For each query i, first, apply nums[indexi] = nums[indexi] + vali, then print the sum of the even values of nums.

Return an integer array answer where answer[i] is the answer to the ith query

Input: nums = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
Output: [8,6,2,4]

 */

    // https://leetcode.com/problems/sum-of-even-numbers-after-queries/
    public static int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int[] result = new int[nums.length];
        int evenSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0)
                evenSum += nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            int value = queries[i][0];
            int index = queries[i][1];

            if (nums[index] % 2 == 0) {
                evenSum -= nums[index];
            }
            nums[index] += value;
            if (nums[index] % 2 == 0)
                evenSum += nums[index];

            result[i] = evenSum;
        }
        return result;
    }


    public static int[] sumEvenAfterQueries1(int[] nums, int[][] queries) { // Time Limit Exceeded
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i, nums[i]);
        }
        for (int j = 0; j < queries.length; j++) {
            int value = queries[j][0];
            int index = queries[j][1];
            int sum = map.get(index) + value;
            map.put(index, sum);

            int evenSum = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() % 2 == 0)
                    evenSum += entry.getValue();
            }
            res.add(evenSum);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }


}
