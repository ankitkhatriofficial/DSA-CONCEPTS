/**
 * Problem Statement: https://leetcode.com/problems/partition-equal-subset-sum/
 */

public class EqualSet {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 70, 40 };
        solution01(arr);
    }

    private static void solution01(int[] arr) {
        // for permutation
        int total_ans = solution01Helper(arr, 0, 0, 0, "", "");
        // for combination
        // int total_ans = solution01Helper(arr, 1, arr[0], 0, arr[0] + " + ", "");
        System.out.println("Total answer = " + total_ans);
    }

    private static int solution01Helper(int[] arr, int idx, int sum1, int sum2, String set1, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1.substring(0, set1.length() - 2) + "= " + set2.substring(0, set2.length() - 2));
                return 1;
            }
            return 0;
        }
        int total_ans = 0;
        total_ans += solution01Helper(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " + ", set2);
        total_ans += solution01Helper(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " + ");
        return total_ans;
    }
}