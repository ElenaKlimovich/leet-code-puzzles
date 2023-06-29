package medium;

import java.util.*;

public class LeetCodeSolutionMedium7 {

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("abcabcbd")); //3

//        int[][] matrix = generateMatrix(3);
//        for (int i[] : matrix) {
//            for (int j : i)
//                System.out.print(j + " ");
//            System.out.println();
//        }

//        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1)); // 2

//        List<String> strings = generateParenthesis(3);

//        System.out.println(checkInclusion("ab", "eidbaooo")); // true
//        System.out.println(checkInclusion("ab", "eidboaoo")); // false
//        System.out.println(checkInclusion("adc", "dcda")); // true
//        System.out.println(checkInclusion("hello", "ooolleoooleh")); // false
//        System.out.println(checkInclusion("ab", "eidbaooo")); // true

        System.out.println(simplifyPath("/home//foo/")); // /home/foo
    }


    // 12. https://leetcode.com/problems/simplify-path
    public static String simplifyPath(String path) {
//        Deque<String> stack = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        String[] dirs = path.split("/");
        for (String dir : dirs) {
            if (!stack.isEmpty() && dir.equals(".."))
                stack.pop();
            else if (!dir.equals("") && !dir.equals(".") && !dir.equals(".."))
                stack.push(dir);
        }

        return "/" + String.join("/", stack);
    }

    // 11. https://leetcode.com/problems/permutation-in-string
    public static boolean checkInclusion(String s1, String s2) {

        int l1 = s1.length();
        int l2 = s2.length();

        int[] c1 = new int[26];

        for (int i=0; i<l1; i++)
            c1[s1.charAt(i) - 'a']++;

        for (int i = 0; i<=l2-l1; i++) {
            int[] c2 = new int[26];
            String window = s2.substring(i, i + l1);
            for (int j=0; j<l1; j++)
                c2[window.charAt(j) - 'a']++;

            if (Arrays.equals(c2, c1))
                return true;
        }

        return false;
    }

    // 10. https://leetcode.com/problems/remove-nth-node-from-end-of-list
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;

        //the gap between slow and fast = n
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        //Move fast & slow to the end, maintaining the gap
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        //Skip the desired node
        slow.next = slow.next.next;

        return dummy.next;
    }

    // https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
    public ListNode deleteMiddle(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;

        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    // 9. https://leetcode.com/problems/perfect-squares
    public int numSquares(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 0;
        for (int i = 1; i <= n; i++) {
            arr[i] = i;
            for (int j = 1; j * j <= i; j++) {
                int temp = 1 + arr[i - j * j];
                if (arr[i] > temp)
                    arr[i] = temp;
            }
        }

        return arr[n];
    }

    // 8. https://leetcode.com/problems/generate-parentheses
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generator(res, "", 0, 0, n);
        return res;
    }

    private static void generator(List<String> res, String current, int open, int close, int n) {
        if (current.length() == 2 * n) {
            res.add(current);
            return;
        }
        if (open < n)
            generator(res, current + "(", open + 1, close, n);
        if (close < open)
            generator(res, current + ")", open, close + 1, n);
    }

    //  7. https://leetcode.com/problems/3sum-closest
    public static int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int res = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            int p1 = i + 1;
            int p2 = nums.length - 1;
            while (p1 < p2) {
                int sum = nums[i] + nums[p1] + nums[p2];
                if (sum == target) {
                    return nums[i] + nums[p1] + nums[p2];
                } else if (sum < target) {
                    p1++;
                } else {
                    p2--;
                }

                int diff = Math.abs(target - sum);
                if (min > diff) {
                    min = diff;
                    res = sum;
                }
            }
        }

        return res;
    }


    //  6.  https://leetcode.com/problems/3sum
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int sum = 0 - nums[i];
            int p1 = i + 1;
            int p2 = nums.length - 1;
            while (p1 < p2) {
                int sum2 = nums[p1] + nums[p2];
                if (sum2 == sum) {
                    res.add(List.of(nums[i], nums[p1], nums[p2]));
                    p1++;
                } else if (sum2 < sum) {
                    p1++;
                } else
                    p2--;
            }
        }

        return new ArrayList<>(res);
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


//    https://leetcode.com/problems/search-in-rotated-sorted-array
//    https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
//    https://leetcode.com/problems/rotate-image
//    https://leetcode.com/problems/group-anagrams
//    https://leetcode.com/problems/merge-intervals
//    https://leetcode.com/problems/lru-cache
//    https://leetcode.com/problems/evaluate-reverse-polish-notation
//    https://leetcode.com/problems/zigzag-iterator
//    https://leetcode.com/problems/reconstruct-itinerary
//    https://leetcode.com/problems/insert-delete-getrandom-o1
//    https://leetcode.com/problems/serialize-and-deserialize-bst
//    https://leetcode.com/problems/max-consecutive-ones-ii
//    https://leetcode.com/problems/subarray-sum-equals-k
//    https://leetcode.com/problems/subarray-sums-divisible-by-k
//    https://leetcode.com/problems/max-consecutive-ones-iii
//    https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element

}
