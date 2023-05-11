package easy;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class LeetCodeSolutionEasy5 {

    private static HashMap<Integer, Integer> trib = new HashMap<>();

    public static void main(String[] args) {
//        System.out.println(">>> " + tribonacci(0));
//        System.out.println(">>> " + tribonacci(1));
//        System.out.println(">>> " + tribonacci(2));
//        System.out.println(">>> " + tribonacci(25));

//        System.out.println(addToArrayForm(new int[]{1,2,6,3,0,7,1,7,1,9,7,5,6,6,4,4,0,0,6,3}, 516));

//        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 9)); // 4
//        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 0)); // 1
//        System.out.println(search(new int[]{-1, 0, 3, 4, 5, 9, 12}, -1)); // 0
//        System.out.println(search(new int[]{0, 1, 2, 3, 4, 5, 6}, 0)); // 0

//        arraySign(new int[] {7,36,96,70,85,23,5,18,4,12,89,92,9,30,53,14,96,32,13,43,37,60,75,7,83,68,20,8,-24,-80,-27,-92,-96,-20,-16,-52,-49,-38});

        System.out.println(findDifference(new int[] {1,2,3}, new int[] {2,4,6})); // [[1,3],[4,6]]
    }

    // https://leetcode.com/problems/find-the-difference-of-two-arrays/
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());

        for (int i : nums2) {
            set1.remove(i);
        }

        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());

        for (int i : nums1) {
            set2.remove(i);
        }

        return List.of(new ArrayList<>(set1), new ArrayList<>(set2));
    }


    // https://leetcode.com/problems/sign-of-the-product-of-an-array/
    public static int arraySign(int[] nums) {
        int n = 1;
        for(int i=0; i< nums.length; i++) {
            if(nums[i] == 0)
                return 0;
            if (nums[i] < 0)
                n *= -1;
            System.out.println(n);
        }
        System.out.println(">> " + n);
        return n > 0 ? 1 : -1;
    }

    // https://leetcode.com/problems/binary-search/
    public static int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid])
                return mid;
            else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static int search0(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length - 1])
            return -1;
        int mid = nums.length / 2, start = 0, end = nums.length - 1;
        while (target != nums[mid]) {
            if (target < nums[mid]) {
                if (mid - start == 1)
                    mid = start;
                else {
                    end = mid - 1;
                    mid = (end - start) / 2;
                }
            } else {
                if (end - mid == 1)
                    mid = start;
                else {
                    start = mid;
                    mid = mid + (end - start) / 2;
                }
            }
        }
        return mid;
    }


    public static List<Integer> addToArrayForm(int[] num, int k) {
        StringBuilder number = new StringBuilder();
        for (int j : num) number.append(j);
        BigInteger res = new BigInteger(number.toString()).add(new BigInteger(String.valueOf(k)));

        List<Integer> resultList = new ArrayList<>();

        String temp = String.valueOf(res);
        for (int i = 0; i < temp.length(); i++)
            resultList.add(Integer.parseInt(String.valueOf(temp.charAt(i))));

        return resultList;
    }

    public static int tribonacci(int n) {
        trib.put(0, 0);
        trib.put(1, 1);
        trib.put(2, 1);
        for (int i = 3; i <= n; i++) {
            trib.putIfAbsent(i, trib.get(i - 3) + trib.get(i - 2) + trib.get(i - 1));
        }
        if (n >= 3)
            trib.put(n, trib.get(n - 3) + trib.get(n - 2) + trib.get(n - 1));
        return trib.get(n);
    }

    public static int tribonacci1(int n) {
        int result = 0;
        if (n == 0)
            result = 0;
        if (n == 1)
            result = 1;
        if (n == 2)
            result = 1;
        for (int i = 3; i <= n; i++) {
            result = tribonacci(n - 3) + tribonacci(n - 2) + tribonacci(n - 1);
        }
//        System.out.println(n + " : " + result);
        return result;
    }
}
