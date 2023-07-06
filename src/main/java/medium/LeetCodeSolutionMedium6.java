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

//        System.out.println(isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {3, 0, 1}, {0, 2}})); // false
//        System.out.println(isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}})); // true
//        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 3, 2, 2}, 2))); // [1, 2]

//        System.out.println(topKFrequent(new String[]{"i","love","leetcode","i","love","coding"}, 2)); // ["i","love"]
//        System.out.println(topKFrequent(new String[]{"the","day","is","sunny","the","the","the","sunny","is","is"}, 4)); // ["the","is","sunny","day"]

//        System.out.println(maximumDetonation(new int[][]{{1, 1, 5}, {10, 10, 5}})); // 1

//        System.out.println(equalPairs(new int[][]{{3, 2, 1}, {1, 7, 6}, {2, 7, 7}})); // 2
//        System.out.println(equalPairs(new int[][]{{3,1,2,2}, {1,4,4,4}, {2,4,2,2}, {2,5,2,2}})); // 3
//        System.out.println(equalPairs(new int[][]{{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}})); // 3

//        System.out.println(longestArithSeqLength(new int[]{3, 6, 9, 12})); // 4

//        System.out.println(kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 3)); // [[1,2],[1,4],[1,6]]
//        System.out.println(kSmallestPairs(new int[]{1,2,2}, new int[]{1,1,2}, 2)); // [[1,2],[1,4],[1,6]]
//        System.out.println(kSmallestPairs(new int[]{1, 2}, new int[]{3}, 2)); // [[1,2],[1,4],[1,6]]

        System.out.println(singleNumber(new int[]{2, 2, 3, 2}));
    }


    // https://leetcode.com/problems/minimum-size-subarray-sum/
    public int minSubArrayLen(int target, int[] nums) {
        int i=0, j=0, sum=0, min=Integer.MAX_VALUE;
        while(i < nums.length) {
            sum += nums[i++];

            while(sum >= target) {
                min = Math.min(min, i - j);
                sum -= nums[j++];
            }
        }
        return min==Integer.MAX_VALUE ? 0 : min;
    }

    // https://leetcode.com/problems/single-number-ii/
    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> fr = new HashMap<>();
        for (int num : nums) {
            Integer frNum = fr.getOrDefault(num, 0);
            if (frNum < 2)
                fr.put(num, frNum+1);
            else
                fr.remove(num);
        }

        return fr.keySet().iterator().next();
    }

    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);

        for (int i = 0; i < nums1.length && i < k; i++)
            pq.offer(new int[]{nums1[i], nums2[0], 0});

        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            result.add(List.of(cur[0], cur[1]));
            if (cur[2] == nums2.length - 1)
                continue;
            pq.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1});
        }

        return result;
    }

    // https://leetcode.com/problems/longest-arithmetic-subsequence/
    public static int longestArithSeqLength(int[] nums) {
        int l = nums.length;
        int res = 1;
        Map<Integer, Integer>[] freqMap = new HashMap[l];
        for (int i = 0; i < l; i++) {
            freqMap[i] = new HashMap<>();
            Map<Integer, Integer> currMap = freqMap[i];
            int currN = nums[i];
            for (int j = 0; j < i; j++) {
                Map<Integer, Integer> prev = freqMap[j];
                int diff = currN - nums[j];
                int freq = prev.getOrDefault(diff, 0) + 1;
                currMap.put(diff, freq);
                freqMap[i] = currMap;
                res = Math.max(res, freq);
            }
        }

        return res + 1;
    }


    // https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/

    public int maxLevelSum(TreeNode root) {

        int levelResult = 0;
        int currentLevel = 0;
        int tempMaxSum = Integer.MIN_VALUE;
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            currentLevel++;

            int size = nodes.size();
            int currentSum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode currentNode = nodes.poll();
                currentSum += currentNode.val;

                TreeNode left = currentNode.left;
                if (left != null)
                    nodes.add(left);

                TreeNode right = currentNode.right;
                if (right != null)
                    nodes.add(right);
            }

            if (tempMaxSum < currentSum) {
                tempMaxSum = currentSum;
                levelResult = currentLevel;
            }
        }

        return levelResult;
    }


    // https://leetcode.com/problems/equal-row-and-column-pairs/
    public static int equalPairs(int[][] grid) {
        int res = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid.length; c++) {
                boolean isEq = true;
                for (int i = 0; i < grid.length; i++) {
                    if (grid[r][i] != grid[i][c]) {
                        isEq = false;
                        break;
                    }
                }
                if (isEq)
                    res++;
            }
        }
        return res;
    }

    // https://leetcode.com/problems/detonate-the-maximum-bombs
    public static int maximumDetonation(int[][] bombs) {
        int max = 0;
        for (int i = 0; i < bombs.length; i++) {
            max = Math.max(max, findMaxCurrent(bombs, i));
        }
        return max;
    }

    private static int findMaxCurrent(int[][] bombs, int i) {
        int n = bombs.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] tested = new boolean[n];
        tested[i] = true;
        q.offer(i);

        int res = 1;

        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int j = 0; j < n; j++) {
                if (!tested[j] && isInside(bombs[curr], bombs[j])) {
                    tested[j] = true;
                    q.offer(j);
                    res++;
                }
            }
        }
        return res;
    }

    private static boolean isInside(int[] b1, int[] b2) {
        long r2 = (long) Math.pow(b1[2], 2);
        long dist2 = (long) Math.pow(b1[0] - b2[0], 2) + (long) Math.pow(b1[1] - b2[1], 2);
        return dist2 <= r2;
    }

    // https://leetcode.com/problems/top-k-frequent-words/
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> frMap = new HashMap<>();

        for (String w : words) {
            frMap.merge(w, 1, (Integer::sum));
        }

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((v1, v2) -> {
                    if (v2.getValue().equals(v1.getValue()))
                        return v1.getKey().compareTo(v2.getKey());
                    return v2.getValue() - v1.getValue();
                });
        pq.addAll(frMap.entrySet());

        List<String> res = new ArrayList<>();
        while (k-- > 0)
            res.add(pq.poll().getKey());

        return res;
    }

    // https://leetcode.com/problems/top-k-frequent-elements/
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frMap = new HashMap<>();

        for (int number : nums) {
            frMap.merge(number, 1, (Integer::sum));
        }

        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue =
                new PriorityQueue<>((v1, v2) -> v2.getValue() - v1.getValue());

        priorityQueue.addAll(frMap.entrySet());

        int[] res = new int[k];
        for (int j = 0; j < k; j++) {
            res[j] = priorityQueue.poll().getKey();
        }

        return res;
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
