package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCodeSolutionEasy {

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
//        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
//        System.out.println(Arrays.toString(twoSum(new int[]{4, 4}, 8)));
//        System.out.println(Arrays.toString(twoSum(new int[]{-1, -2, -3, -4, -5}, -8)));

        System.out.println(romanToInt("LVIII")); // 58
        System.out.println(romanToInt("MCMXCIV")); // 1994

    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    public static int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index+1;
    }

    // https://leetcode.com/problems/roman-to-integer/
    public static int romanToInt(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == 'M' && i > 0 && s.charAt(i - 1) == 'C') {
                result += 900;
            } else if (s.charAt(i) == 'M') {
                result += 1000;

            }

            if (s.charAt(i) == 'D' && i > 0 && s.charAt(i - 1) == 'C') {
                result += 400;
                System.out.println("i: " + i + " result: " + result);
            } else if (s.charAt(i) == 'D') {
                result += 500;
            }

            if (s.charAt(i) == 'C' && i > 0 && s.charAt(i - 1) == 'X') {
                result += 90;
            } else if (i + 1 < s.length() && s.charAt(i) == 'C' && (s.charAt(i + 1) == 'M' || s.charAt(i + 1) == 'D')) {
                result += 0;
            } else if (s.charAt(i) == 'C') {
                result += 100;
            }

            if (s.charAt(i) == 'L' && i > 0 && s.charAt(i - 1) == 'X') {
                result += 40;
            } else if (s.charAt(i) == 'L') {
                result += 50;
            }

            if (s.charAt(i) == 'X' && i > 0 && s.charAt(i - 1) == 'I') {
                result += 9;
            } else if (i <= s.length() - 2 && s.charAt(i) == 'X' && (s.charAt(i + 1) == 'C' || s.charAt(i + 1) == 'L'))
                result += 0;
            else if (s.charAt(i) == 'X') {
                result += 10;
            }

            if (s.charAt(i) == 'V' && i > 0 && s.charAt(i - 1) == 'I') {
                result += 4;
            } else if (s.charAt(i) == 'V') {
                result += 5;
            }

            if (i == s.length() - 2 && s.charAt(i) == 'I' && (s.charAt(i + 1) == 'X' || s.charAt(i + 1) == 'V')) {
//                System.out.println("i: " + i + " char: " + s.charAt(i) + " result: " + result);
            } else if (s.charAt(i) == 'I') {
                result += 1;
            }
        }
        return result;
    }

    public int romanToInteger(String s) {

        Map<Character, Integer> map = new HashMap();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char current = s.charAt(i);
            char next = s.charAt(i + 1);
            if (map.get(current) < map.get(next))
                result -= map.get(current);
            else
                result += map.get(current);
        }
        return result + map.get(s.charAt(s.length() - 1));
    }


    // https://leetcode.com/problems/two-sum/
    private static int[] twoSum1(int[] nums, int target) {

        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            int first = nums[i];
            for (int j = 1; j < nums.length; j++) {
                int second = nums[j];
                if (i != j && first + second == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    // faster
    private static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer diff = target - nums[i];
            if (map.containsKey(diff)) {
                int[] result = {map.get(diff), i};
                return result;
            }

            map.put(nums[i], i);
        }
        return null;
    }

}


