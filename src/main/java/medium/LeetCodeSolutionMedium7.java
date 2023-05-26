package medium;

public class LeetCodeSolutionMedium7 {

    public static void main(String[] args) {

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
            temp = currentSum/10;

            pointer.next = new ListNode(currentSum%10);
            pointer = pointer.next;
        }

        return dummy.next;
    }


//    https://leetcode.com/problems/longest-substring-without-repeating-characters
//    https://leetcode.com/problems/longest-palindromic-substring
//    https://leetcode.com/problems/3sum
//    https://leetcode.com/problems/3sum-closest
//    https://leetcode.com/problems/remove-nth-node-from-end-of-list
//    https://leetcode.com/problems/generate-parentheses
//    https://leetcode.com/problems/search-in-rotated-sorted-array
//    https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
//    https://leetcode.com/problems/rotate-image
//    https://leetcode.com/problems/group-anagrams
//    https://leetcode.com/problems/merge-intervals
//    https://leetcode.com/problems/spiral-matrix-ii
//    https://leetcode.com/problems/simplify-path
//    https://leetcode.com/problems/validate-binary-search-tree
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
