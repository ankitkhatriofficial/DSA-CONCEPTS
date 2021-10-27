
/**
 * Problem Statement: https://leetcode.com/problems/k-th-symbol-in-grammar/
 */
public class KthSymbol {
    public static void main(String[] args) {
        int n = 2;
        int k = 2;
        System.out.println(KthSymbol_(n, k));
    }

    private static int KthSymbol_(int n, int k) {
        int pow = 1 << (n - 1);
        return KthSymbol_(n, k, pow) ? 1 : 0;
    }

    private static boolean KthSymbol_(int n, int k, int len) {
        if (n == 1)
            return false;
        if (k < len / 2)
            return KthSymbol_(n - 1, k, len / 2);
        else
            return !KthSymbol_(n - 1, k - len / 2, len / 2);
    }
}