package easy;

import java.util.*;

public class LeetCodeSolutionEasy6 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); // [0,1]
//        System.out.println(isValid("()[]{}")); // true

//        ListNode list14 = new ListNode(4);
//        ListNode list12 = new ListNode(2, list14);
//        ListNode list1 = new ListNode(1, list12);
//
//        ListNode list24 = new ListNode(4);
//        ListNode list23 = new ListNode(3, list24);
//        ListNode list2 = new ListNode(1, list23);
//        ListNode node = mergeTwoLists(list1, list2); // //list1 = [1,2,4], list2 = [1,3,4]  Output: [1,1,2,3,4,4]

//        System.out.println(strStr("sadbutsad", "sad")); // 0

//        merge(new int[]{1, 3, 5, 0, 0, 0}, 3, new int[]{2, 2, 6}, 3); // [1,2,2,3,5,6]
//        merge(new int[]{4, 5, 6, 7, 0, 0, 0}, 4, new int[]{1, 2, 3}, 3);
//        merge(new int[]{0}, 1, new int[]{1}, 1);
        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
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

    //   4. https://leetcode.com/problems/implement-strstr
    public static int strStr(String haystack, String needle) {
//        return haystack.indexOf(needle);


        int lh = haystack.length();
        int ln = needle.length();

        if (lh < ln)
            return -1;

        for (int i = 0; i < lh - ln; i++) {
            int j = 0;

            while (j < ln && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == ln)
                return i;
        }

        return -1;
    }

    //   5. https://leetcode.com/problems/merge-sorted-array
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1, j = n - 1, l = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[l] = nums1[i];
                i--;
            } else {
                nums1[l] = nums2[j];
                j--;
            }
            l--;
        }

        System.out.println(Arrays.toString(nums1));
    }

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