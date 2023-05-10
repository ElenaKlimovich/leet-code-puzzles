import org.w3c.dom.ranges.Range;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LeetCodeSolutionEasy4 {

    public static void main(String[] args) {
//        System.out.println(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
//        System.out.println(checkIfPangram("leetcode"));

//        System.out.println(isToeplitzMatrix(new int[][] {new int[]{1,2,3,4}, new int[]{5,1,2,3},new int[]{9,5,1,2}})); // true
//        System.out.println(isToeplitzMatrix(new int[][] {new int[]{1,2,3}, new int[]{2,1,4},new int[]{9,5,1}})); // false

//        maximum69Number(9996); // 9999
//        maximum69Number(9669); //9969
//        maximum69Number(9999); //9999

//        String str = "leAaetcode";
//        StringBuilder builder = new StringBuilder(str);
//        builder.replace(2, 4, "");
//        System.out.println(builder);
//
//        System.out.println(makeGood("leEeetcode")); // leetcode
//        System.out.println(makeGood("abBAcC")); // ""
//        System.out.println(makeGood("s")); // s
//        System.out.println("".equals(makeGood("djrDdRJD")));
//        System.out.println("".equals(makeGoodRecur("djrDdRJD")));
//        System.out.println(makeGoodRecur("djrDdRJD"));
//        System.out.println(makeGoodWithStack("leEeetcode"));

//        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 5
//        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1})); // 0
//        System.out.println(maxProfit(new int[]{7, 6, 4, 1, 3})); // 2

//        System.out.println(findKthPositive(new int[]{2, 3, 4, 7, 11}, 5)); // 9
//        System.out.println(findKthPositive(new int[]{1, 2, 3, 4}, 2)); // 6
//        System.out.println(findKthPositive(new int[]{1,3,5,6,7,8,11,13,14,16,17,18,19,20,21,23,24,25,26,28,29,30,31,34,35,36,37,38,41,43,44,47,50,51,53,54,56,57,58,59,60,62,63,65,67,68,69,70,71,72,73,74,76,78,80,81,83,84,85,88,89,90,91,92,93,95,97,98,102,103,104,105,108,109,110,111,112,113,114,117,120,123,124,125,127,128,129,130,131,132,133,135,136,137,138,139,141,142,145,146,148,149,150,151,153,154,155,156,161,162,164,167,168,169,170,171,172,175,176,178,179,181,182,184,191,193,195,196,199,201,202,204,205,208,210,214,215,217,219,221,222,224,226,228,229,230,231,232,234,235,236,240,242,246,248,249,251,252,253,254,255,256,257,258,259,260,261,262,265,267,269,272,273,275,278,279,280,281,282,283,284,285,286,287,289,291,292,293,296,297,298,299,303,305,306,308,312,313,315,316,318,320,323,324,327,330,332,335,337,340,342,343,344,346,349,350,352,353,354,356,357,359,360,362,366,367,369,370,374,375,376,377,378,379,382,384,386,390,392,393,394,395,396,399,400,401,403,406,411,413,415,416,420,424,425,426,427,429,430,432,434,435,436,437,438,439,440,441,442,443,444,446,447,448,449,452,455,456,458,459,460,461,462,463,464,465,466,467,469,470,471,472,477,479,480,483,484,486,488,489,490,491,492,493,494,495,500,501,503,504,506,508,510,513,514,515,516,517,527,531,533,534,535,536,542,543,546,547,548,549,550,553,556,559,561,562,563,566,567,569,571,572,576,578,579,581,582,583,584,586,589,591,592,593,594,595,598,600,601,602,603,605,606,607,609,611,612,613,614,616,617,621,622,624,625,626,627,630,631,633,635,636,637,639,640,643,644,646,647,648,649,650,651,652,654,658,660,661,662,663,664,665,667,668,669,672,673,678,679,683,685,686,687,689,690,691,692,693,695,696,697,701,702,703,704,707,709,711,714,717,718,719,720,721,723,724,725,726,728,729,730,733,735,736,737,738,740,742,745,746,747,750,754,755,757,759,761,763,765,768,771,773,774,775,776,779,780,781,782,783,784,787,788,789,790,791,792,794,795,797,798,800,801,805,806,808,810,811,812,814,816,819,822,824,825,826,828,831,833,835,838,841,842,844,845,846,847,849,853,854,855,857,858,861,862,866,868,869,870,874,878,882,884,885,888,889,890,892,893,897,900,903,905,906,907,908,911,913,916,918,920,921,922,924,925,926,928,929,930,932,933,934,936,937,938,940,942,944,946,949,953,954,956,957,958,961,962,964,965,966,969,972,973,974,976,977,978,979,980,981,982,984,985,986,988,993,996,997,999}, 724)); // 6

        System.out.println(diagonalSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})); // 25
    }
/*
 1 2 3 4
 3 4 5 6
 7 2 4 1
 0 3 2 1

 03 12 21 30

 */

    // https://leetcode.com/problems/matrix-diagonal-sum/
    public static int diagonalSum(int[][] mat) {
        int k = mat.length;
        int sum = 0;
        int second = 0;
        for (int i = 0; i < k; i++) {
            sum += mat[i][i];
            if (k - 1 - i != i)
                second += mat[i][k - 1 - i];
        }
        return sum + second;
    }

    // https://leetcode.com/problems/kth-missing-positive-number/
    public static int findKthPositive(int[] arr, int k) {

        List<Integer> numbers = IntStream.range(1, 2000).boxed().collect(Collectors.toList());
        List<Integer> arrList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        numbers.removeAll(arrList);
        return numbers.get(k - 1);
    }

    public static int maxProfit(int[] prices) {
        int min = prices[0];
        int maxMoney = 0;

        for (int price : prices) {
            if (min > price) {
                min = price;
                continue;
            }
            int current = price - min;

            if (maxMoney < current) {
                maxMoney = current;
            }
        }

        return maxMoney;
    }

    // https://leetcode.com/problems/make-the-string-great/
    public static String makeGoodWithStack(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && Math.abs(stack.peek() - ch) == 32)
                stack.pop();
            else
                stack.add(ch);
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty())
            builder.append(stack.pop());
        return builder.reverse().toString();

    }


    public static String makeGoodRecur(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (Math.abs(s.charAt(i + 1) - s.charAt(i)) == 32)
                return makeGoodRecur(s.substring(0, i) + s.substring(i + 2));
        }
        return s;

    }


    public static String makeGood(String s) {
        if (s.isEmpty() || s.toLowerCase().equals(s) || s.toUpperCase().equals(s))
            return s;
        return makeGoodHelper(s);
    }


    public static String makeGoodHelper(String s) {
        StringBuilder temp = new StringBuilder(s);

        for (int i = 0; i < s.length() - 1; i++) {
            boolean lowerCase1 = Character.isLowerCase(s.charAt(i));
            boolean lowerCase2 = Character.isLowerCase(s.charAt(i + 1));
            boolean sameLetter = Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(i + 1));
            if (((lowerCase1 && !lowerCase2)
                    || (!lowerCase1 && lowerCase2))
                    && sameLetter) {
                char c1 = s.charAt(i);
                char c2 = s.charAt(i + 1);
                temp.replace(i, i + 2, "__");
                i++;
            }

        }

        String strTemp = temp.toString().replaceAll("__", "");
        if (s.length() == strTemp.length()) {
            return s;
        } else {
            return makeGoodHelper(strTemp);
        }
    }

    // https://leetcode.com/problems/maximum-69-number/
    public static int maximum69Number(int num) {

        int result = Integer.parseInt(String.valueOf(num).replaceFirst("6", "9"));
        System.out.println(result);
        return result;
    }


    private void printDigits(int num) {
        num = 12345;
        for (int i = String.valueOf(num).length() - 1; i >= 0; i--) {

            int n = (int) Math.pow(10, i);
            int x = num / n;
            System.out.println(x);
            num -= x * n;

        }
    }

    // https://leetcode.com/problems/toeplitz-matrix/
    public static boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1])
                    return false;
            }
        }
        return true;
    }

    private static boolean checkDiagonalInSquareMatrix(int[][] matrix) {
        Set<Integer> diagonal = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[matrix.length - 1].length; j++) {
                if (i == j && matrix[i] == matrix[j])
                    diagonal.add(matrix[i][j]);
            }
        }
        return diagonal.size() == 1;
    }

    // https://leetcode.com/problems/remove-element/
    public int removeElement(int[] nums, int val) {
        int first = 0, last = nums.length - 1;
        while (first <= last) {
            if (nums[first] == val) {
                if (nums[last] != val) {
                    nums[first] = nums[last];
                    first++;
                }
                last--;
            } else {
                first++;
            }
        }
        return first;
    }


    // https://leetcode.com/problems/check-if-the-sentence-is-pangram/

    public static boolean checkIfPangram(String sentence) {

        int[] letters = new int[26];
        for (int i = 0; i < sentence.length(); i++) {
            char currentLetter = sentence.charAt(i);
            int letterNumber = currentLetter - 'a';
            letters[letterNumber]++;
        }

        for (int letter : letters) {
            if (letter == 0)
                return false;
        }

        return true;
    }
}
