import java.util.*;

/**
 * Problem Statement:
 * https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/gold-mine-2-official/ojquestion
 */

public class GoldMine2 {
    static int max = 0;

    public static void getMaxGold(int[][] arr) {

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] > 0) {
                    int gold = collectGold(arr, i, j, visited);
                    max = Math.max(max, gold);
                }
            }
        }
    }

    private static int collectGold(int[][] arr, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || visited[i][j] || arr[i][j] == 0)
            return 0;

        visited[i][j] = true;
        int gold = 0;
        gold = Math.max(gold, collectGold(arr, i - 1, j, visited));
        gold = Math.max(gold, collectGold(arr, i + 1, j, visited));
        gold = Math.max(gold, collectGold(arr, i, j + 1, visited));
        gold = Math.max(gold, collectGold(arr, i, j - 1, visited));
        visited[i][j] = false;
        return gold + arr[i][j];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        scn.close();
        getMaxGold(arr);
        System.out.println(max);
    }
}
