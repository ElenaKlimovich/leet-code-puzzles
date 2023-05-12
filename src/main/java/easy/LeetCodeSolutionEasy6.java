package easy;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LeetCodeSolutionEasy6 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); // [0,1]
//        System.out.println(isValid("()[]{}")); // true

        ListNode list14 = new ListNode(4);
        ListNode list12 = new ListNode(2, list14);
        ListNode list1 = new ListNode(1, list12);

        ListNode list24 = new ListNode(4);
        ListNode list23 = new ListNode(3, list24);
        ListNode list2 = new ListNode(1, list23);
        ListNode node = mergeTwoLists(list1, list2); // //list1 = [1,2,4], list2 = [1,3,4]  Output: [1,1,2,3,4,4]
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


    //  3.  https://leetcode.com/problems/merge-two-sorted-lists
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode pointer = result;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                pointer.next = list1;
                list1 = list1.next;
            } else {
                pointer.next = list2;
                list2 = list2.next;
            }
            pointer = pointer.next;
        }

        pointer.next = list1 == null ? list2 : list1;

        return result.next;
    }

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
