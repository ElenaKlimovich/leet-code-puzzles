package medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCodeSolutionMedium3 {

    public static void main(String[] args) {
//        System.out.println(equationsPossible(new String[]{"", ""}));

        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);

        ListNode listNode55 = new ListNode(5);
        ListNode listNode33 = new ListNode(3, listNode55);
        ListNode listNode22 = new ListNode(2, listNode33);
        ListNode listNode11 = new ListNode(1, listNode22);

        ListNode x = removeNthFromEnd(listNode1, 2);
        System.out.println(x.equals(listNode11));
    }

    // Definition for singly-linked list
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() { }
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /*
    Input: head = [1,2,3,4,5], n = 2
    Output: [1,2,3,5]
     */
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode slow = start, fast = start;

        //Move fast in front so that the gap between slow and fast becomes n
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        //Move fast to the end, maintaining the gap
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Skip the desired node
        slow.next = slow.next.next;
        return start.next;
    }



    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummyStart = new ListNode(0);
        dummyStart.next = head;
        ListNode slow = dummyStart, fast = dummyStart;

        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummyStart.next;
    }
    /*

    Input: equations = ["a==b","b!=a"]
    Output: false

    Input: equations = ["b==a","a==b"]
    Output: true

     */
    // https://leetcode.com/problems/satisfiability-of-equality-equations/
    public static boolean equationsPossible(String[] equations) {

        char[] e1 = equations[0].toCharArray();
        char[] e2 = equations[1].toCharArray();
        Map<Integer, Character> map1 = new HashMap<>();
        Map<Integer, Character> map2 = new HashMap<>();


        return true;
    }



}
