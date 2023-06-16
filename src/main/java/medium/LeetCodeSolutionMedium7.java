package medium;

import java.util.*;

public class LeetCodeSolutionMedium7 {

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("abcabcbd")); //3

        int[][] matrix = generateMatrix(3);
        for (int i[] : matrix) {
            for (int j : i)
                System.out.print(j + " ");
            System.out.println();
        }
    }

    //  5.  https://leetcode.com/problems/spiral-matrix-ii
    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int left = 0;
        int top = 0;
        int right = n - 1;
        int bottom = n - 1;
        int k = 1;

        while (left <= right && top <= bottom) {

            for (int j = left; j <= right; j++, k++) {
                res[top][j] = k;
            }
            top++;

            for (int i = top; i <= bottom; i++, k++) {
                res[i][right] = k;
            }
            right--;

            if (top <= bottom) {
                for (int j = right; j >= left; j--, k++) {
                    res[bottom][j] = k;
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--, k++) {
                    res[i][left] = k;
                }
                left++;
            }
        }

        return res;
    }

    //  4.  https://leetcode.com/problems/validate-binary-search-tree
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;
        int rootVal = root.val;
        TreeNode left = root.left;
        TreeNode right = root.right;
        return (min == null || rootVal > min)
                && (max == null || rootVal < max)
                && isValid(left, min, rootVal)
                && isValid(right, rootVal, max);
    }

    //  3. https://leetcode.com/problems/longest-palindromic-substring
    public String longestPalindrome(String s) {
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            String oddStr = expandWindow(s, i, i);
            if (res.length() < oddStr.length())
                res = oddStr;

            String evenStr = expandWindow(s, i, i + 1);
            if (res.length() < evenStr.length())
                res = evenStr;
        }
        return res;
    }

    private String expandWindow(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return s.substring(start + 1, end);
    }

    //  2. https://leetcode.com/problems/longest-substring-without-repeating-characters
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int i = 0;
        int j = 0;
        Set<Character> window = new HashSet<>();

        while (j < s.length()) {
            if (!window.contains(s.charAt(j))) {
                window.add(s.charAt(j++));
                max = Math.max(max, window.size());
            } else
                window.remove(s.charAt(i++));
        }
        return max;
    }


    //  1. https://leetcode.com/problems/add-two-numbers
    private static class ListNode {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode();
        ListNode pointer = dummy;
        int temp = 0;
        while (l1 != null || l2 != null || temp > 0) {
            int currentSum = 0;
            if (l1 != null) {
                currentSum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                currentSum += l2.val;
                l2 = l2.next;
            }

            currentSum += temp;
            temp = currentSum / 10;

            pointer.next = new ListNode(currentSum % 10);
            pointer = pointer.next;
        }

        return dummy.next;
    }


//    https://leetcode.com/problems/3sum
//    https://leetcode.com/problems/3sum-closest
//    https://leetcode.com/problems/remove-nth-node-from-end-of-list
//    https://leetcode.com/problems/generate-parentheses
//    https://leetcode.com/problems/search-in-rotated-sorted-array
//    https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
//    https://leetcode.com/problems/rotate-image
//    https://leetcode.com/problems/group-anagrams
//    https://leetcode.com/problems/merge-intervals
//    https://leetcode.com/problems/simplify-path
//    https://leetcode.com/problems/lru-cache
//    https://leetcode.com/problems/evaluate-reverse-polish-notation
//    https://leetcode.com/problems/summary-ranges
//    https://leetcode.com/problems/perfect-squares
//    https://leetcode.com/problems/zigzag-iterator
//    https://leetcode.com/problems/reconstruct-itinerary
//    https://leetcode.com/problems/insert-delete-getrandom-o1
//    https://leetcode.com/problems/serialize-and-deserialize-bst
//    https://leetcode.com/problems/max-consecutive-ones-ii
//    https://leetcode.com/problems/subarray-sum-equals-k
//    https://leetcode.com/problems/permutation-in-string
//    https://leetcode.com/problems/subarray-sums-divisible-by-k
//    https://leetcode.com/problems/max-consecutive-ones-iii
//    https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element

}
