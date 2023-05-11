package easy;

import java.util.*;

public class LeetCodeSolutionEasy3 {

    public static void main(String[] args) {
//        System.out.println(isValid("{}()[]"));
//        System.out.println(isValid("{]"));
//        System.out.println(isValid("{[]}")); // true
//        System.out.println(isValid("(([]){})")); // true
//        System.out.println(isValid("([[][]{({}({}))}])")); // true

//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));

//        System.out.println(isAnagram("anagram", "nagaram"));
//        System.out.println(isAnagram("rat", "car"));
//        System.out.println(isAnagram("aacc", "ccac")); // false

//        System.out.println(addBinary("1", "1"));
//        System.out.println(addBinary("100", "1"));

//        float a = 35 / 0; // Runtime exception
//        float b = 35.0f / 0; // Infinity
//        System.out.println(a);

//        System.out.println(climbStairs(5));


        removeDuplicates("");
    }

    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string
    public static String removeDuplicates(String s) {
        s = "abbaca";
        Stack<Character> letters = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            System.out.println(i + " " + s.charAt(i));
            if(!letters.isEmpty() && letters.peek() == s.charAt(i))
                letters.pop();
            else
                letters.add(s.charAt(i));

        }

        StringBuilder sb = new StringBuilder();
        while(!letters.isEmpty())
            sb.append(letters.pop());

        return sb.reverse().toString();
    }

    // https://leetcode.com/problems/climbing-stairs/
    public static int climbStairs(int n) {
        if (n == 1)
            return 1;

        int var1 = 1;
        int var2 = 2;

        for (int i = 3; i <= n; i++) {
            int res = var1 + var2;
            var1 = var2;
            var2 = res;
        }
        return var2;
    }

    // https://leetcode.com/problems/add-binary/
    public static String addBinary(String a, String b) {
        int la = a.length() - 1;
        int lb = b.length() - 1;
        StringBuilder res = new StringBuilder();
        int sum = 0;
        int temp = 0;

        while (la >= 0 || lb >= 0) {
            sum = temp;
            if (la >= 0)
                sum += a.charAt(la--) - '0';
            if (lb >= 0)
                sum += b.charAt(lb--) - '0';

            res.append(sum % 2);
            temp = sum / 2;
        }
        if (temp != 0)
            res.append(temp);

        return res.reverse().toString();
    }


    public static String addBinary1(String a, String b) {
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();

        int l = Math.max(a.length(), b.length());

        char[] res = new char[l + 1];

        for (int i = l - 1; i >= 0; i--) {
            if (oneOrZero(aChar[i], bChar[i]) == 0)
                res[i] = '0';
            if (oneOrZero(aChar[i], bChar[i]) == 1)
                res[i] = '1';
            if (oneOrZero(aChar[i], bChar[i]) == 10) {
                res[i] = '0';
                res[i - 1] = '1';
            }
        }
        return Arrays.toString(res);
    }

    public static int oneOrZero(char n, char m) {
        if ('0' == n && '0' == m)
            return 0;
        if (('0' == n && '1' == m)
                || ('1' == n && '0' == m))
            return 1;
        else // ('1' == n && '1' == m)
            return 10;
    }

    // https://leetcode.com/problems/valid-anagram/
    public static boolean isAnagram(String s, String t) {
        int[] abc = new int[26];
        for (int i = 0; i < s.length(); i++)
            abc[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++)
            abc[t.charAt(i) - 'a']--;
        for (int i : abc)
            if (i != 0)
                return false;

        return true;
    }


    public static boolean isAnagram_wrong(String s, String t) {
        if (s.length() != t.length())
            return false;
        List<Character> first = new ArrayList<>();
        for (Character ch : s.toCharArray()) {
            first.add(ch);
        }
        System.out.println(first);
        List<Character> second = new ArrayList<>();
        for (Character ch : t.toCharArray()) {
            second.add(ch);
        }
        System.out.println(second);

        return first.containsAll(second);
    }

    public static boolean isPalindrome(String s) {
        if (s.isEmpty())
            return true;

        int head = 0;
        int tail = s.length() - 1;
        char first, last;
        while (head <= tail) {
            first = Character.toLowerCase(s.charAt(head));
            last = Character.toLowerCase(s.charAt(tail));
            if (!Character.isLetterOrDigit(first)) {
                head++;
            } else if (!Character.isLetterOrDigit(last)) {
                tail--;
            } else {
                if (first != last) {
                    return false;
                }
                head++;
                tail--;
            }
        }
        return true;
    }

    // https://leetcode.com/problems/valid-parentheses/
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);
            if (bracket == '{' || bracket == '(' || bracket == '[')
                stack.push(bracket);
            else {
                if (stack.isEmpty())
                    return false;
                else if (bracket == '}' && stack.pop() != '{')
                    return false;
                else if (bracket == ')' && stack.pop() != '(')
                    return false;
                else if (bracket == ']' && stack.pop() != '[')
                    return false;
            }
        }
        return stack.isEmpty();
    }


    public static boolean isValid_withReplace(String s) {
        System.out.println(s);
        int newLength;
        do {
            newLength = s.length();
            System.out.println("newLength: " + newLength);
            s = s.replace("{}", "")
                    .replace("()", "")
                    .replace("[]", "");

            System.out.println(s);
            System.out.println("s.length() : " + s.length());
        } while (newLength != s.length());
        return s.length() == 0;
    }

    public static boolean isValid_Wrong(String s) {
        s = s.replaceAll("\\{}", "")
                .replaceAll("\\(\\)", "")
                .replaceAll("\\[]", "");
        System.out.println(s);
        return s.length() == 0;
    }

}
