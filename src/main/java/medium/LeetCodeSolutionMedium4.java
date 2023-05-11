package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCodeSolutionMedium4 {

    public static void main(String[] args) {
//        System.out.println(numRollsToTarget(2, 6, 7)); // 6
//        System.out.println(numRollsToTarget(30, 30, 500)); // 222616187 = modulo 1e9 + 7

        System.out.println(minCost("aabaa", new int[]{1, 2, 3, 4, 1})); // 2
        System.out.println(minCost("aaabbbabbbb", new int[]{3, 5, 10, 7, 5, 3, 5, 5, 4, 8, 1})); // 26

        System.out.println(countAndSay(4)); // 1211
    }

    // https://leetcode.com/problems/count-and-say/
    public static String countAndSay(int n) {
        if (n == 1)
            return "1";
        String countAndSayString = countAndSay(n - 1);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < countAndSayString.length(); i++) {
            int counter = 1;

            while (i < countAndSayString.length() - 1 &&
                    countAndSayString.charAt(i) == countAndSayString.charAt(i + 1)) {
                counter++;
                i++;

            }

            builder.append(counter)
                    .append(countAndSayString.charAt(i));
        }

        return builder.toString();
    }


    // https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
    public static int minCost(String colors, int[] neededTime) {
        int result = 0;
        int pointer = 0;
        for (int i = 1; i < colors.length(); i++) {
            if (colors.charAt(pointer) == colors.charAt(i)) {
                if (neededTime[pointer] < neededTime[i]) {
                    result += neededTime[pointer];
                    pointer = i;
                } else
                    result += neededTime[i];
            } else {
                pointer = i;
            }
        }
        return result;
    }

    // https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
    public static int numRollsToTarget1(int diceNumber, int faceNumber, int targetSum) {
        int result = 0;


        return result;
    }

    static Map<String, Integer> map = new HashMap<>();

    public static int numRollsToTarget(int d, int f, int target) {
        if (target < d || target > f * d) {
            return 0;
        }
        if (d == 1) {
            return (target <= f) ? 1 : 0;
        }
        String key = "" + d + f + target;
        if (!map.containsKey(key)) {
            int sum = 0;
            for (int i = 1; i <= f; i++) {
                sum += numRollsToTarget(d - 1, f, target - i);
                sum = sum % 1000000007;
            }

            map.put(key, sum);
        }
        return map.get(key);
    }

}
