package easy;

import java.util.*;
import java.util.stream.Collectors;

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
//        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);

//        1,2,2,3,4,4,3
//        TreeNode node13 = new TreeNode(3);
//        TreeNode node14 = new TreeNode(4);
//        TreeNode node24 = new TreeNode(4);
//        TreeNode node23 = new TreeNode(3);
//        TreeNode node12 = new TreeNode(2, node13, node14);
//        TreeNode node22 = new TreeNode(2, node24, node23);
//        TreeNode node = new TreeNode(1, node12, node22);
//
//        System.out.println(isSymmetric(node)); // true

//        System.out.println(isPalindrome("a roza upala na lapu Azora"));
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(countPrimes(10)); // 4 (2, 3, 5, 7)

//        ListNode node5 = new ListNode(5);
//        ListNode node4 = new ListNode(4, node5);
//        ListNode node3 = new ListNode(3, node4);
//        ListNode node2 = new ListNode(2, node3);
//        ListNode node = new ListNode(1, node2);

//        reverseList(node); //  5 4 3 2 1
//
//        MyQueue obj = new MyQueue();
//        obj.push(1);
//        int param_2 = obj.pop();
//        int param_3 = obj.peek();
//        boolean param_4 = obj.empty();

//        moveZeroes(new int[]{0, 1, 0, 3, 12}); //  [1,3,12,0,0]

//        System.out.println(Arrays.toString(intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2}))); //[2,2]
//        System.out.println(Arrays.toString(intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}))); //[4,9]

        System.out.println(compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'})); // 6 - a2b2c3
        System.out.println(compress(new char[]{'b', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'})); // 4 - ba12

    }

    // https://leetcode.com/problems/design-hashset
    // II
    class MyHashSet {

        int size = 10000;
        List<List<Integer>> mySet;
        public MyHashSet() {
            mySet = new ArrayList<>(size);
            for(int i=0; i<size; i++) {
                mySet.add(null);
            }
        }

        public void add(int key) {
            int index = key%size;
            List<Integer> inner = mySet.get(index);
            if(inner == null) {
                inner = new LinkedList<>();
                inner.add(key);
                mySet.set(index, inner);
            }
            else {
                if(!inner.contains(key))
                    inner.add(key);
            }
        }

        public void remove(int key) {
            int index = key%size;
            List<Integer> inner = mySet.get(index);
            if(inner != null)
                inner.remove(Integer.valueOf(key));
        }

        public boolean contains(int key) {
            int index = key%size;
            List<Integer> inner = mySet.get(index);
            return inner != null && inner.contains(key);
        }
    }

    // I
//    class MyHashSet {
//
//        boolean[] mySet;
//        public MyHashSet() {
//            mySet = new boolean[1000001];
//        }
//
//        public void add(int key) {
//            mySet[key] = true;
//        }
//
//        public void remove(int key) {
//            mySet[key] = false;
//        }
//
//        public boolean contains(int key) {
//            return mySet[key];
//        }
//    }

    // https://leetcode.com/problems/design-parking-system/
    class ParkingSystem {

        int big;
        private int medium;
        private int small;

        public ParkingSystem(int big, int medium, int small) {
            this.big = big;
            this.medium = medium;
            this.small = small;
        }

        public boolean addCar(int carType) {
            if (carType == 1) {
                big--;
                return big >= 0;
            } else if (carType == 2) {
                medium--;
                return medium >= 0;
            } else {
                small--;
                return small >= 0;
            }
        }
    }


    //   14. https://leetcode.com/problems/number-of-recent-calls
    class RecentCounter {
        Queue<Integer> q;

        public RecentCounter() {
            q = new LinkedList<>();
        }

        public int ping(int t) {
            while (!q.isEmpty() && t - 3000 > q.peek())
                q.poll();
            q.add(t);
            return q.size();
        }
    }

    //   13. https://leetcode.com/problems/string-compression
    public static int compress(char[] chars) {
        int pointer = 0;
        int i = 0;
        while (i < chars.length) {
            int counterOfSameLetters = 0;
            char current = chars[i];
            while (i < chars.length && current == chars[i++]) {
                counterOfSameLetters++;
            }
            chars[pointer++] = current;
            String nStr = String.valueOf(counterOfSameLetters);
            if (nStr.length() > 1) {
                int k = 0;
                while (k < nStr.length()) {
                    chars[pointer++] = nStr.charAt(k++);
                }
            }
        }
        return pointer;
    }

    //   12. https://leetcode.com/problems/intersection-of-two-arrays-ii
    public static int[] intersect(int[] nums1, int[] nums2) {

        List<Integer> n1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> res = new ArrayList<>();

        for (int n : nums2) {
            if (n1.contains(n)) {
                res.add(n);
                n1.remove((Integer) n);
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    //   11. https://leetcode.com/problems/move-zeroes
    public static void moveZeroes(int[] nums) {

        // II
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (p != i) {
                    nums[p] = nums[i];
                    nums[i] = 0;
                }
                p++;
            }
        }

        // I
//        int p = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != 0) {
//                nums[p++] = nums[i];
//            }
//        }
//        for (int i = p; i < nums.length; i++) {
//            nums[i] = 0;
//        }
        System.out.println(Arrays.toString(nums));
    }

    // 10. https://leetcode.com/problems/implement-queue-using-stacks
    static class MyQueue {

        Stack<Integer> inPuts = new Stack<>();
        Stack<Integer> outPuts = new Stack<>();

        public MyQueue() {

        }

        public void push(int x) {
            inPuts.push(x);
        }

        public int pop() {
            if (outPuts.isEmpty()) {
                while (!inPuts.isEmpty())
                    outPuts.push(inPuts.pop());
            }
            return outPuts.pop();
        }

        public int peek() {
            if (outPuts.isEmpty()) {
                while (!inPuts.isEmpty())
                    outPuts.push(inPuts.pop());
            }
            return outPuts.peek();
        }

        public boolean empty() {
            return inPuts.isEmpty() && outPuts.isEmpty();
        }
    }

    //  9.  https://leetcode.com/problems/reverse-linked-list
    public static ListNode reverseList(ListNode head) {
        ListNode reversed = null;

        while (head != null) {
            ListNode tempNext = head.next;
            head.next = reversed;
            reversed = head;
            head = tempNext;
        }
        return reversed;
    }

    //  8. https://leetcode.com/problems/count-primes
    public static int countPrimes(int n) {
        if (n < 3)
            return 0;

        int count = 0;
        boolean[] isNotPrime = new boolean[n];
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        for (int i = 2; i < n; i++) {
            if (!isNotPrime[i]) {
                count++;

                for (int j = i; j < n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
        return count;
    }

    //  7.  https://leetcode.com/problems/valid-palindrome
    public static boolean isPalindrome(String s) {
        System.out.println(s.length());
        if (s.isEmpty())
            return true;

        int headPointer = 0;
        int tailPointer = s.length() - 1;

        while (headPointer <= tailPointer) {
            char head = Character.toLowerCase(s.charAt(headPointer));
            char tail = Character.toLowerCase(s.charAt(tailPointer));
            if (!Character.isLetterOrDigit(head))
                headPointer++;
            else if (!Character.isLetterOrDigit(tail))
                tailPointer--;
            else {
                if (head != tail)
                    return false;
                headPointer++;
                tailPointer--;
            }
        }

        return true;
    }

    //  1.  https://leetcode.com/problems/two-sum
    public static int[] twoSum0(int[] nums, int target) {
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

    //  1.  https://leetcode.com/problems/two-sum
    // method of 2 pointers - only for sorted array
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int p1 = 0;
        int p2 = nums.length - 1;

        while (p1 < p2) {
            if (nums[p1] + nums[p2] == target) {
                result[0] = p1;
                result[1] = p2;
                break;
            } else if (nums[p1] + nums[p2] > target)
                p2--;
            else
                p1++;
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

//  6.  https://leetcode.com/problems/symmetric-tree

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        return areNodesSymmetric(root, root);
    }

    static boolean areNodesSymmetric(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null)
            return true;
        if (n1 == null || n2 == null)
            return false;
        return (n1.val == n2.val && areNodesSymmetric(n1.left, n2.right) && areNodesSymmetric(n2.left, n1.right));
    }

}
