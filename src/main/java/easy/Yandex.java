package easy;

import java.util.*;

public class Yandex {
    public static void main(String[] args) {
//        sortVowels("lEetcOde");
        countNicePairs(new int[]{13,10,35,24,76}); // 4
    }

    public static int countNicePairs(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        //List<Integer> pairs = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            StringBuilder reverse = new StringBuilder(String.valueOf(nums[i]));
            reverse.reverse();
            int rev = Integer.parseInt(reverse.toString());
            List<Integer> indexes = map.getOrDefault(nums[i] - rev, new ArrayList<>());
            indexes.add(i);
            map.put(nums[i] - rev, indexes);
        }
        long result = 0;
        for (List<Integer> indexes : map.values()) {
            result += indexes.size() * (indexes.size() - 1) / 2;
        }
        
        int modulo = 1000000007;
        int r = 1599960000 % modulo;
        int r1 = 999949972 % modulo;
        long i2 = 999949972L * 1000000007;
        return (int) (result % modulo);
    }

    public static String sortVowels(String s) {
        List<Character> v = new ArrayList<>();
        List<Integer> indxs = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                v.add(s.charAt(i));
                indxs.add(i);
            }
        }

        System.out.println();
        System.out.println(v);

        //Arrays.sort(v.toArray());
        Collections.sort(v);

        System.out.println();
        System.out.println(v);

        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < indxs.size(); i++) {
            int j = indxs.get(i);

            sb.replace(j, j + 1, String.valueOf(v.get(i)));// = v.get(i);
        }
        return sb.toString();
    }

    private static boolean isVowel(char c) {
        return Set.of('a', 'e', 'i', 'o', 'u').contains(Character.toLowerCase(c));
    }

    public static void main0(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String nextLine = in.nextLine();

        List<Integer> complexities = new ArrayList<>();
        String[] numbers = nextLine.split(" ");
        for (String num : numbers) {
            complexities.add(Integer.parseInt(num));
        }
        assert (n == complexities.size());
        System.out.println(findSumComplexity(n, complexities));
    }

    public static int findSumComplexity(int n, List<Integer> complexities) {
        if (n % 2 != 0) {
            return -1;
        }

        int l = 0;
        int r = complexities.size() - 1;
        int sumComplexity = complexities.get(l) + complexities.get(r);
        l++;
        r--;
        while (l < r) {
            if (complexities.get(l) + complexities.get(r) != sumComplexity) {
                return -1;
            }
            l++;
            r--;
        }
        return sumComplexity;
    }

    public static int countPalindromicTime(int n, int m) {
        int counterResult = 1;
        int curTime = 1;
        if (n < m) {
            int mLen = String.valueOf(m).length();
            while (curTime < n) {
                int curLen = String.valueOf(curTime).length();
                StringBuilder time = new StringBuilder();
                if (curLen < mLen) {
                    for (int i = curLen; i < mLen; i++) {
                        time.append("0");
                    }
                }
                time.append(curTime);
                String rTime = time.reverse().toString();
                if (Integer.parseInt(rTime) < m) {
                    counterResult++;
                }
                curTime++;
            }
        } else {
            int nLen = String.valueOf(n).length();
            while (curTime < m) {
                int curLen = String.valueOf(curTime).length();
                StringBuilder time = new StringBuilder();
                if (curLen < nLen) {
                    for (int i = curLen; i < nLen; i++) {
                        time.append("0");
                    }
                }
                time.append(curTime);
                String rTime = time.reverse().toString();
                if (Integer.parseInt(rTime) < n) {
                    counterResult++;
                }
                curTime++;
            }
        }

        return counterResult;
    }


    public static int countPalindromicTime0(int n, int m) {
        int h = 1;
        int min = 0;

//        String sHour = "";
//        String sMin = "";
//
//        int l = Math.max(n, m);
//
//        if (n < m) {
//            l = String.valueOf(m).length();
//        } else {
//            l = String.valueOf(n).length();
//        }
//        int nLen = String.valueOf(n).length();
//        int mLen = String.valueOf(m).length();
//
//        int zCount = Math.abs(nLen - mLen);
//
//        StringBuilder zeros = new StringBuilder();
//        while (zCount-- > 0) {
//            zeros.append("0");
//        }

        int counterResult = 1;


        while (h < n) {

            StringBuilder time = new StringBuilder();
            if (h < 10) {
                time.append("0").append(h);
            } else {
                time.append(h);
            }
            String hTime = time.toString();
            String rTime = time.reverse().toString();

            if (Integer.parseInt(rTime) < m) {
                counterResult++;
                System.out.println(hTime + " : " + rTime);
            }

            h++;
        }
        return counterResult;
    }

}
