package easy;

import org.w3c.dom.Node;

import java.util.*;

public class LeetCodeSolution {

    public static void main(String[] args) {
//        System.out.println(isPowerOfFour(0));
//        System.out.println(isPowerOfFour(16));

//        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));

//        System.out.printf("%, d", Integer.MIN_VALUE);
//        System.out.println();
//        System.out.printf("%, d", Integer.MAX_VALUE);

//        assert myAtoi("   -42") == -42;
//        System.out.println(myAtoi("   -42"));
//        System.out.println(myAtoi("42"));
//        System.out.println(myAtoi("4193 with words"));
//        System.out.println(myAtoi(" with words 4193"));
//        System.out.println(myAtoi("-91283472332"));
//        System.out.println(myAtoi("-3.14159"));
//        System.out.println(myAtoi("3.14159"));
//        System.out.println(myAtoi("+-12"));
//        System.out.println(myAtoi("9223372036854775808"));
//        System.out.println(myAtoi("-92233720368547758080"));


//        System.out.println(tree2str(new TreeNode(1,
//                new TreeNode(2, new TreeNode(4), null),
//                new TreeNode(3))));  // "1(2(4))(3)"


        Node node14 = new Node(14);
        Node node13 = new Node(13);
        Node node12 = new Node(12);
        Node node11 = new Node(11, List.of(node14));
        Node node10 = new Node(10);
        Node node9 = new Node(9, List.of(node13));
        Node node8 = new Node(8, List.of(node12));
        Node node7 = new Node(7, List.of(node11));
        Node node6 = new Node(6);
        Node node5 = new Node(5, List.of(node9, node10));
        Node node4 = new Node(4, List.of(node8));
        Node node3 = new Node(3, List.of(node6, node7));
        Node node2 = new Node(2);
        Node node1 = new Node(1, List.of(node2, node3, node4, node5));

//        System.out.println(node1.children);
        System.out.println(levelOrder(node1));  // [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
    }


    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int sumEven = 0;
        for (int number : nums) {
            if (number % 2 == 0) {
                sumEven += number;
            }
        }
        int[] result = new int[queries.length];
        int numsIndex;
        for (int i = 0; i < queries.length; i++) {
            numsIndex = queries[i][1];
            if (nums[numsIndex] % 2 == 0) {
                sumEven -= nums[numsIndex];
            }
            nums[numsIndex] += queries[i][0];
            if (nums[numsIndex] % 2 == 0) {
                sumEven += nums[numsIndex];
            }
            result[i] = sumEven;
        }
        return result;
    }




    // https://leetcode.com/problems/n-ary-tree-level-order-traversal/
    // BREADTH_FIRST_SEARCH
    public static List<List<Integer>> levelOrder(Node root) {
        if (root == null)
            return Collections.emptyList();

        List<List<Integer>> res = new ArrayList<>();

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            List<Integer> currentValues = new ArrayList<>();
            int currentLevelSize = nodeQueue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                System.out.println(i);
                Node tempNode = nodeQueue.poll();
                System.out.println("  >>  tempNode : " + tempNode.val);
                currentValues.add(tempNode.val);
                System.out.println(tempNode.children);
                if (tempNode.children != null)
                    nodeQueue.addAll(tempNode.children);
            }
            res.add(currentValues);
            System.out.println(res);
        }

        return res;
    }


    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    // https://leetcode.com/problems/construct-string-from-binary-tree/
    public static String tree2str(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder res = new StringBuilder(root.val + "");
        if (root.left != null && root.right != null) {
            res.append("(").append(tree2str(root.left)).append(")").append("(").append(tree2str(root.right)).append(")");
        }
        if (root.left != null && root.right == null)
            res.append("(").append(tree2str(root.left)).append(")");
        if (root.left == null && root.right != null)
            res.append("()").append("(").append(tree2str(root.right)).append(")");

        return res.toString();
    }

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


    // https://leetcode.com/problems/string-to-integer-atoi/
    public static int myAtoi(String s) {

        int sign = 1;
        long result = 0;

        s = s.trim();
        for (int i = 0; i < s.length(); i++) {

            if ((s.charAt(i) == '-' || s.charAt(i) == '+') && i != 0)
                break;

            if (s.charAt(i) == '-' && i == 0) {
                sign = -1;
                continue;
            } else if (s.charAt(i) == '+' && i == 0) {
                continue;
            }

            if (Character.isDigit(s.charAt(i))) {
                result = result * 10 + Character.getNumericValue(s.charAt(i));

                if ((result < 0 || result > Integer.MAX_VALUE) && sign == 1)
                    return Integer.MAX_VALUE;
                else if (result < 0 || (sign * result) < Integer.MIN_VALUE)
                    return Integer.MIN_VALUE;
            }

            if (s.charAt(i) != '-' && s.charAt(i) != '+' && !Character.isDigit(s.charAt(i)))
                break;
        }

        return (int) (sign * result);
    }

    // https://leetcode.com/problems/power-of-four/
    public static boolean isPowerOfFour(int n) {
        if (n <= 0)
            return false;
        if (n == 1)
            return true;
        int x = 0;
        while (n % 4 == 0) {
            x = n / 4;
            n = x;
        }
        return x == 1;
    }

    // https://leetcode.com/problems/container-with-most-water/
    public static int maxArea(int[] height) {
        int result = 0, temp, i = 0, j = height.length - 1;
        while (i < j) {
            if (height[i] < height[j]) {
                temp = height[i] * (j - i);
                i++;
            } else {
                temp = height[j] * (j - i);
                j--;
            }
            if (temp > result)
                result = temp;
        }
        return result;
    }
}
