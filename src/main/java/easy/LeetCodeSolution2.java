package easy;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCodeSolution2 {
    public static void main(String[] args) {


//        System.out.println("Original Array: " + Arrays.toString(findOriginalArray(new int[]{16, 1, 2, 3, 6, 8})));

        String str1 = new String("string");
        String str2 = new String("string");

        System.out.println(str1==str2); // false

        String a1 = str1.intern();
        String a2 = str2.intern();

        System.out.println(str1==str2);
        System.out.println(a1==a2);


    }

    public static int[] findOriginalArray(int[] changed) {

        int[] original = new int[]{};
        if (changed.length % 2 != 0) {
            return original;
        }

        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList();

        Arrays.sort(changed);

        for (int element : changed) {
            if (queue.isEmpty() || element != queue.peek()) {
                queue.offer(element * 2);
                result.add(element);
            } else {
                queue.poll();
            }
        }

        return !queue.isEmpty() ? original : result.stream().mapToInt(i -> i).toArray();
    }

    // https://leetcode.com/problems/find-original-array-from-doubled-array/submissions/
    public static int[] findOriginalArray1(int[] changed) {  // Time Limit Exceeded

        int[] original = new int[]{};
        if (changed.length % 2 != 0) {
            return original;
        }

        List<Integer> arr = Arrays.stream(changed).sorted().boxed().collect(Collectors.toList());
        List<Integer> res = new ArrayList<>();

        boolean isDoubled = false;

        for (int i = 0; i < changed.length; i++) {
            if (arr.isEmpty()) {
                isDoubled = true;
                break;
            }
            Integer min = arr.get(0);
            Integer doubledMin = 2 * min;

            if (arr.remove(doubledMin)) {
                arr.remove(min);
                res.add(min);
            }
        }
        if (isDoubled) {
            original = res.stream().mapToInt(Integer::intValue).toArray();
        }

        return original;
    }
}
