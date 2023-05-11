package medium;

public class LeetCodeSolutionMedium2 {

    public static void main(String[] args) {
        System.out.println(concatenatedBinary(1));
        System.out.println(concatenatedBinary(3));
        System.out.println(concatenatedBinary(12));
        System.out.println(concatenatedBinaryMath(3));
    }

    public static int concatenatedBinary(int n) {
        long result = 0;
        long modulo = 1000000007;
        int digitsCount = 0; // length of the binary string
        for (int i = 0; i <= n; i++) {
            if ((i & (i - 1)) == 0)
                digitsCount++;
            result = ((result << digitsCount) + i) % modulo;
        }

        return  (int)result;
    }

    public static int concatenatedBinaryMath(int n) {
        final long modulo = (long) (1e9 + 7);
        long result = 0;
        for (int i = 1; i <= n; i++) {
            // For each i, we shift left the position of result with * 2,
            // while shifting right the position of i with / 2.
            int temp = i;
            while (temp > 0) {
                temp /= 2;
                result *= 2;
            }
            // Add the i to the result and get the remainder of modulo.
            result = (result + i) % modulo;
        }
        return (int) result;
    }
}
