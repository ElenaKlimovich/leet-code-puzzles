package medium;

import java.util.*;

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

//        System.out.println(pairSum(node)); // [1,2,3,4] -> 5

//        System.out.println(findSmallestSetOfVertices(6, List.of(List.of(0, 1), List.of(0, 2), List.of(2, 5), List.of(3, 4), List.of(4, 2)))); // [0,3]

        System.out.println(isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {3, 0, 1}, {0, 2}})); // false
        System.out.println(isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}})); // true
    }


    // https://leetcode.com/problems/is-graph-bipartite/
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
//                if (!bfsColor(graph, i, colors))
//                    return false;

                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                colors[i] = 1;
                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    for (int adjacent : graph[current]) {
                        if (colors[adjacent] == colors[current]) {
                            return false;
                        } else if (colors[adjacent] == 0) {
                            colors[adjacent] = -colors[current];
                            queue.add(adjacent);
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean bfsColor(int[][] graph, int vertex, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        colors[vertex] = 1;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int adjacent : graph[current]) {
                if (colors[adjacent] == colors[current]) {
                    return false;
                } else if (colors[adjacent] == 0) {
                    colors[adjacent] = -colors[current];
                    queue.add(adjacent);
                }
            }
        }
        return true;
    }


    // https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/description/
    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // I
//        Set<Integer> froms = new HashSet<>();
//        Set<Integer> tos = new HashSet<>();
//
//        for (int i = 0; i<edges.size(); i++) {
//            froms.add(edges.get(i).get(0));
//            tos.add(edges.get(i).get(1));
//        }
//        froms.removeAll(tos);
//        return new ArrayList<>(froms);

        // II
        boolean[] incomingVer = new boolean[n];
        List<Integer> res = new ArrayList<>();
        for (List<Integer> edge : edges) {
            int toVertix = edge.get(1);
            if (!incomingVer[toVertix])
                incomingVer[toVertix] = true;
        }

        for (int i = 0; i < n; i++) {
            if (!incomingVer[i])
                res.add(i);
        }

        return res;
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
        for (int i = 0; i < values.size() / 2; i++) {
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
