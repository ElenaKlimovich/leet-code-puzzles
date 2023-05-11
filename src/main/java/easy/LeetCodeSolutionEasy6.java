package easy;

import java.util.*;

public class LeetCodeSolutionEasy6 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); // [0,1]
        System.out.println(isValid("()[]{}")); // true

    }

    //  1.  https://leetcode.com/problems/two-sum
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int n = nums.length;
        // key = nums[i], value = index
        Map<Integer, Integer> tempMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (tempMap.containsKey(target - nums[i])) {
                result[0] = i;
                result[1] = tempMap.get(target - nums[i]);
            }
            tempMap.put(nums[i], i);
        }
        return result;
    }

    //  2.  https://leetcode.com/problems/valid-parentheses
    public static boolean isValid(String s) {
        char[] brackets = s.toCharArray();

        Deque<Character> tempStack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char br = s.charAt(i);
            if (br == '{' || br == '(' || br == '[') {
                tempStack.push(br);
            } else if (tempStack.element() == '{' && br == '}')
                tempStack.pop();

            else if (tempStack.element() == '(' && br == ')')
                tempStack.pop();

            else if (tempStack.element() == '[' && br == ']')
                tempStack.pop();

            else
                return false;
        }

        return tempStack.isEmpty();
    }


//    https://leetcode.com/problems/merge-two-sorted-lists
//    https://leetcode.com/problems/implement-strstr
//    https://leetcode.com/problems/merge-sorted-array
//    https://leetcode.com/problems/symmetric-tree
//    https://leetcode.com/problems/valid-palindrome
//    https://leetcode.com/problems/count-primes
//    https://leetcode.com/problems/reverse-linked-list
//    https://leetcode.com/problems/implement-queue-using-stacks
//    https://leetcode.com/problems/move-zeroes
//    https://leetcode.com/problems/intersection-of-two-arrays-ii
//    https://leetcode.com/problems/string-compression
//    https://leetcode.com/problems/number-of-recent-calls

}
