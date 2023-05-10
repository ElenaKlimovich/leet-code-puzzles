import java.util.*;

public class LeetCodeSolutionMedium5 {

    public static void main(String[] args) {

//        System.out.println(intToRoman(3)); // III
//        System.out.println(intToRoman(49)); // III
//
//        System.out.println(checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6)); // true

//        System.out.println(computeArea(-3, 0, 3, 4, 0, -1, 9, 2)); // 45
//        System.out.println(computeArea(-2, -2, 2, 2, 3, 3, 4, 4)); // 17
//        System.out.println(computeArea(-2, -2, 2, 2, -1, 4, 1, 6)); // 20

//        System.out.println(Arrays.toString(sortArray(new int[]{23, 2, 6, 4, 7})));

//        System.out.println(minPathSum(new int[][] {{1,3,1},{1,5,1},{4,2,1}})); // 7

        System.out.println(partitionString("abacaba")); // 4 / ("a","ba","cab","a") and ("ab","a","ca","ba")
    }

    // https://leetcode.com/problems/optimal-partition-of-string/
    public static int partitionString(String s) {
        Set<Character> letters = new HashSet<>();
        int n = 1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(letters.contains(ch)) {
                letters.clear();
                n++;
            }
            letters.add(ch);
        }
        return n;
    }

    // https://leetcode.com/problems/minimum-path-sum/
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Create a minSumTable table to store the minimum sum path to each cell
        int[][] minSumTable = new int[m][n];
        minSumTable[0][0] = grid[0][0];

        // Fill the first row
        for (int i = 1; i < n; i++) {
            minSumTable[0][i] = minSumTable[0][i - 1] + grid[0][i];
        }

        // Fill the first column
        for (int i = 1; i < m; i++) {
            minSumTable[i][0] = minSumTable[i - 1][0] + grid[i][0];
        }

        // Fill the rest of the minSumTable table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                minSumTable[i][j] = Math.min(minSumTable[i - 1][j], minSumTable[i][j - 1]) + grid[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(minSumTable[i][j] + " ");
            }
            System.out.println();
        }

        return minSumTable[m - 1][n - 1];
    }

    public static int[] sortArray(int[] nums) {
        int i = 0;
        int[] result = new int[nums.length];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int n : nums)
            priorityQueue.offer(n);
        while (!priorityQueue.isEmpty())
            result[i++] = priorityQueue.poll();
        return result;

    }

    public static int[] sortArray1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            sort(nums);
        }
        return nums;
    }


    public static int[] sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                int temp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = temp;
            }
        }

        return nums;
    }

    // https://leetcode.com/problems/rectangle-area/
    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int result = 0;

        int as = (ax2 - ax1) * (ay2 - ay1);
        int bs = (bx2 - bx1) * (by2 - by1);

        System.out.println(as);
        System.out.println(bs);

        int cy = Math.min(ay2, by2) - Math.max(ay1, by1);
        int cx = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        System.out.println(cx + "  " + cy);
        int intersect = cx * cy;
        if (cx < 0 || cy < 0)
            result = as + bs;
        else
            result = as + bs - intersect;

        return result;
    }

    // https://leetcode.com/problems/integer-to-roman/
    public static String intToRoman(int num) {
//        Map<String, Integer> numMap = Map.of(
//                "I", 1,
//                "IV", 4,
//                "V", 5,
//                "IX", 9,
//                "X", 10,
//                "XL", 40,
//                "L", 50,
//                "XC", 90,
//                "C", 100,
//                "CD", 400,
//                "D", 500,
//                "CM",900);
//                "M",1000);


        StringBuilder result = new StringBuilder();

        int[] numerals = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int i = 0;
        while (num > 0) {
            if (num - numerals[i] >= 0) {
                result.append(romans[i]);
                num -= numerals[i];
            } else
                i++;
        }

        return result.toString();
    }


    // https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
    public int maxLength(List<String> arr) {
        return 0;
    }

    // https://leetcode.com/problems/continuous-subarray-sum/
    public static boolean checkSubarraySum(int[] nums, int k) {
        // maintain a hash map to store <sum % k, index>
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sum %= k;
            // case 1
            if (sum == 0 && i > 0) {
                return true;
            }
            // case 2
            if (map.containsKey(sum) && i - map.get(sum) > 1) {
                return true;
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }

        }
        return false;
    }
}
