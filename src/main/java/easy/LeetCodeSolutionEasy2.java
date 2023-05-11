package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeetCodeSolutionEasy2 {

    public static void main(String[] args) {
//        System.out.println("s'teL ekat edoCteeL tsetnoc".equals(reverseWords("Let's take LeetCode contest")));
//        System.out.println("edoCteeL".equals(reverseWords("LeetCode")));

//        guessNumber(10);
//        System.out.println(guessNumber(3));
        System.out.println(addDigits(1239));
    }

    // https://leetcode.com/problems/add-digits/
    public static int addDigits(int num) {
        int temp = sumDigits(num);
        while (String.valueOf(temp).length() != 1) {
            temp = sumDigits(temp);
        }
        return temp;
    }

    public static int sumDigits(int num) {
        String strNum = String.valueOf(num);
        int temp = 0;
        for (int i = 0; i < strNum.length(); i++) {
            temp += Integer.parseInt(String.valueOf(strNum.charAt(i)));
        }
        return temp;
    }

    // https://leetcode.com/problems/guess-number-higher-or-lower/

    /**
     * @param num your guess
     * @return -1 if num is higher than the picked number
     * 1 if num is lower than the picked number
     * otherwise return 0
     * int guess(int n);
     */
    static int guess(int num) {
        int compare = Integer.compare(3, num);
        System.out.println(compare + "  :: num = " + num);
        return compare;
    }

    public static int guessNumber(int n) {
        if (n == 1)
            return guess(1) == 0 ? 1 : 0;

        int min = 0;
        int max = n;
        List<Integer> maxList = new ArrayList<>();
        List<Integer> minList = new ArrayList<>();
        int result = (max - min) / 2;
        while (guess(result) != 0) {
            if (result > 0) {
                System.out.println(" >0 " + " min=" + min + " max=" + max);
                min = min + (max - min) / 2;
                minList.add(min);
                if (minList.size() > 5)
                    break;
            } else {
                System.out.println(" <0 " + " min=" + min + " max=" + max);
                max = min + (max - min) / 2;
                maxList.add(max);
                if (max == 0)
                    break;
            }
            if ((max - min) == 1)
                result = min + 1;
            else
                result = min + (max - min) / 2;
//            System.out.println("result = " + result);
        }
        System.out.println("minList = " + minList);
        System.out.println("maxList = " + maxList);
        System.out.println("result = " + result);
        return result;
    }

    public static int guessNumber1(int n) {
        int min = 0;
        int max = n;
        int result = (max - min) / 2;
        while (guess(result) != 0) {
            if (guess(result) > 0)
                min = (max - min) / 2;
            else
                max = min + (max - min) / 2;

            result = min + (max - min) / 2;
        }
        return result;
    }

    public static String reverseWords(String s) {
        String result = Arrays.stream(s.split(" "))
                .map(word -> {
                    char[] letters = word.toCharArray();
                    char[] reversed = new char[letters.length];
                    for (int i = reversed.length - 1; i >= 0; i--) {
                        char letter = letters[letters.length - 1 - i];
                        reversed[i] = letter;
                    }
                    return new String(reversed);
                }).collect(Collectors.joining(" "));
        return result.trim();
    }
}

