package medium;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeSolutionMedium6 {

    public static void main(String[] args) {
//        ListNode node5 = new ListNode(5);
//        ListNode node4 = new ListNode(4, node5);
//        ListNode node3 = new ListNode(3, node4);
//        ListNode node2 = new ListNode(2, node3);
//        ListNode node = new ListNode(1, node2);
//        swapNodes(node, 2); //[1,2,3,4,5] -> [1,4,3,2,5]

        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node = new ListNode(1, node2);

//        swapPairs(node); //[1,2,3,4] -> [2,1,4,3]

        System.out.println(pairSum(node)); // [1,2,3,4] -> 5
    }

    // https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
    public static int pairSum(ListNode head) {
        ListNode temp = head;
        List<Integer> values = new ArrayList<>();
        while (temp != null) {
            values.add(temp.val);
            temp = temp.next;
        }
        int maxSum = 0;
        for (int i=0; i<values.size() / 2; i++) {
            int tempMax = values.get(i) + values.get(values.size() - 1 - i);
            if (maxSum < tempMax)
                maxSum = tempMax;
        }
        return maxSum;
    }

    // https://leetcode.com/problems/swap-nodes-in-pairs/
    public static ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode pointer = dummyNode;

        while (pointer != null) {
            ListNode node1 = pointer.next;
            ListNode node2 = null;

            if (node1 != null)
                node2 = node1.next;

            if (node2 != null) {
                ListNode nextNode2 = node2.next;
                node2.next = node1;
                pointer.next = node2;
                node1.next = nextNode2;
                pointer = node1;
            } else
                break;
        }

        return dummyNode.next;
    }

    // https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
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

    public static ListNode swapNodes(ListNode head, int k) {

        ListNode node1 = head;

        while (k > 1) {
            node1 = node1.next;
            k--;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = node1;

        while (fastPointer.next != null) {
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }

        ListNode node2 = slowPointer;

        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;

        return head;
    }
}
