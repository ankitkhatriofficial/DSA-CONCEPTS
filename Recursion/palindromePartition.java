/**
 * Problem Statement:
 * https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/all-palindromic-partitions-official/ojquestion
 */

public class PalindromePartition {
    public static void main(String[] args) {
        String str = "pep";
        // int min = solution01(str, "");
        int min = solution02(str, 0, "");
        System.out.println("minimum Partition = " + (min));
    }

    /* WAY 2 To SOLVE THE PROBLEM */
    private static int solution02(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return 1;
        }
        int minCut = 0;
        for (int cut = idx; cut < str.length(); cut++) {
            String prefix = str.substring(idx, cut + 1);
            if (isPaldindrome(prefix)) {
                minCut = Math.min(minCut, solution02(str, cut + 1, ans + "(" + prefix + ")")) + 1;
            }
        }
        return minCut;
    }

    /* WAY 1 TO SOLVE THE PROBLEM */
    private static int solution01(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 0;
        }
        int min = 0;
        for (int i = 0; i < str.length(); i++) {
            String prefix = str.substring(0, i + 1);
            String roq = str.substring(i + 1);
            if (isPaldindrome(prefix))
                min = Math.min(min, solution01(roq, ans + "(" + prefix + ") ")) + 1;
        }
        return min;
    }

    private static boolean isPaldindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
