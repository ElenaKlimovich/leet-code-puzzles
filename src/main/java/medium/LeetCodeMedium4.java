package medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeetCodeMedium4 {
    public static void main(String[] args) {
//        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3));

//        System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})); // [1,2,3,6,9,8,7,4,5]
//        System.out.println(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}})); // [1,2,3,4,8,12,11,10,9,5,6,7]
//        System.out.println(Arrays.deepToString(generateMatrix(3))); // [[1,2,3],[8,9,4],[7,6,5]]
//        System.out.println(Arrays.deepToString(generateMatrix(4)));

//        minimumDeleteSum("delete", "leet"); //403 ; let

//        permute(new int[]{1,2}); // [1,2], [2,1]

//        System.out.println(wordBreak("leetcode", List.of("leet","code"))); // true

        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // O(log n);  4

    }

    // https://leetcode.com/problems/search-in-rotated-sorted-array/
    public static int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(start <= end) {
            int m = start + (end - start) / 2;
            int mid = nums[m];

            if ((mid < nums[0]) != (target < nums[0])) {
                mid = target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            if (target > mid)
                start = m + 1;
            else if (target < mid)
                end = m - 1;
            else
                return m;
        }
        return -1;
    }

    // https://leetcode.com/problems/unique-binary-search-trees-ii
    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }

    private List<TreeNode> generate(int s, int e) {
        List<TreeNode> result = new ArrayList<>();
        if(s > e) {
            result.add(null);
            return result;
        }

        for(int i=s; i<=e; i++) {
            List<TreeNode> leftChildren = generate(s, i-1);
            List<TreeNode> rightChildren = generate(i+1, e);

            for (TreeNode leftTree: leftChildren) {
                for (TreeNode rightTree: rightChildren) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    result.add(root);
                }
            }
        }
        return result;
    }

    // https://leetcode.com/problems/word-break
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] checker = new boolean[n+1];
        checker[n] = true;
        for(int i=n-1; i>=0; i--) {
            for(int j=i+1; j<=n && !checker[i]; j++) {
                checker[i] = checker[j] && wordDict.contains(s.substring(i,j));
            }
        }
        return checker[0];
    }

    // https://leetcode.com/problems/letter-combinations-of-a-phone-number
    public static List<String> letterCombinations(String digits) {
        if (digits.length()==0)
            return List.of();
        int[] numbers = new int[digits.length()];
        for (int i=0; i<numbers.length; i++) {
            numbers[i] = digits.charAt(i) - '0';
        }

        List<String> letters = new ArrayList<>();
        for (char c: digits.toCharArray()) {
            letters.add(phoneButtons.get(c - '0'));
        }

        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
//        createCombinations(result, letters);
        createCombinations(result, numbers, current, 0);
        return result;
    }

    private static Map<Integer, String> phoneButtons = Map.of(2, "abc", 3, "def", 4, "ghi", 5, "jkl", 6, "mno", 7, "pqrs", 8, "tuv", 9, "wxyz");

    private static void createCombinations(List<String> result, int[] numbers, StringBuilder current, int index) {

        if(current.length() == numbers.length) {
            result.add(current.toString());
            return;
        }

        int digit = numbers[index];
        String buttonLetters = phoneButtons.get(digit);
        for (int i=0; i<buttonLetters.length(); i++) {
            current.append(buttonLetters.charAt (i));
            createCombinations(result, numbers, current, index+1);
            current.deleteCharAt(current.length()-1);
        }
    }

    // https://leetcode.com/problems/permutations
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        createPermutation(new ArrayList<>(), result, nums);
        return result;
    }

    private static void createPermutation(List<Integer> current, List<List<Integer>> res, int[] nums) {
        if(current.size() == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }

        for (int num : nums) {
            if (!current.contains(num)) {
                current.add(num);
                createPermutation(current, res, nums);
                current.remove(current.size() - 1);
            }
        }
    }

    // https://leetcode.com/problems/combinations
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        createCombinations(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void createCombinations(int start, int n, int k, List<Integer> current, List<List<Integer>>result) {
        if(k == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for(int i=start; i<=n; i++) {
            current.add(i);
            createCombinations(i+1, n, k-1, current, result);
            current.remove(current.size()-1);
        }
    }

    // https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
    public static int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i<= n1; i++)
            dp[i][0] = dp[i - 1][0] + s1.charAt(i-1);

        for (int j = 1; j<= n2; j++)
            dp[0][j] = dp[0][j - 1] + s2.charAt(j-1);

        for (int i = 1; i<= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int a = dp[i-1][j] + s1.charAt(i-1);
                    int b = dp[i][j-1] + s2.charAt(j-1);
                    dp[i][j] = Math.min(a, b);
                }
            }
        }

        return dp[n1][n2];
    }

    // https://leetcode.com/problems/spiral-matrix-ii/
    public static int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        List<Integer> numbers = IntStream.rangeClosed(1, n * n).boxed().collect(Collectors.toList());

        int left = 0;
        int top = 0;
        int right = n - 1;
        int bottom = n - 1;
        int count = 1;

        while (left <= right && top <= bottom) {

            for (int i = left; i <= right; i++, count++) {
                matrix[top][i] = count;
            }
            top++;

            for (int j = top; j <= bottom; j++, count++) {
                matrix[j][right] = count;
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--, count++) {
                    matrix[bottom][i]=count;
                }
                bottom--;
            }

            if (left <= right) {
                for (int j = bottom; j >= top; j--, count++) {
                    matrix[j][left] = count;
                }
                left++;
            }
        }

        return matrix;
    }

    // https://leetcode.com/problems/spiral-matrix/
    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> spiral = new ArrayList<>();

        int lines = matrix.length, columns = matrix[0].length;
        int left = 0;
        int top = 0;
        int right = columns - 1;
        int bottom = lines - 1;

        while (left <= right && top <= bottom) {

            for (int i = left; i <= right; i++) {
                spiral.add(matrix[top][i]);
            }
            top++;

            for (int j = top; j <= bottom; j++) {
                spiral.add(matrix[j][right]);
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    spiral.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (left <= right) {
                for (int j = bottom; j >= top; j--) {
                    spiral.add(matrix[j][left]);
                }
                left++;
            }
        }

        return spiral;
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> result = new ArrayList<>();

        int leftPointer = 0;
        int rightPointer = arr.length - 1;

        while (rightPointer - leftPointer >= k) {
            int leftDiff = Math.abs(arr[leftPointer] - x);
            int rightDiff = Math.abs(arr[rightPointer] - x);

            if (leftDiff <= rightDiff)
                rightPointer--;
            else
                leftPointer++;
        }

        while (leftPointer <= rightPointer) {
            result.add(arr[leftPointer]);
            leftPointer++;
        }

        return result;
    }
}
