/**
 * Problem Statement: https://www.lintcode.com/problem/909/
 */
public class AndroidPattern {
    public static void main(String[] args) {
        int n = 3;
        int m = 6;
        System.out.println(solve01(n, m));
    }

    private static int solve01(int n, int m) {
        int ans = 0;
        int[][] mat = new int[10][10];
        mat[1][3] = mat[3][1] = 2;
        mat[1][7] = mat[7][1] = 4;
        mat[3][9] = mat[9][3] = 6;
        mat[7][9] = mat[9][7] = 8;
        mat[1][9] = mat[3][7] = mat[4][6] = mat[2][8] = mat[9][1] = mat[7][3] = mat[6][4] = mat[8][2] = 5;

        boolean[] visited = new boolean[10];

        for (int moves = n; moves <= m; moves++) {
            ans += solve01Helper(moves, 1, mat, visited) * 4;
            ans += solve01Helper(moves, 2, mat, visited) * 4;
            ans += solve01Helper(moves, 5, mat, visited);
        }
        return ans;
    }

    private static int solve01Helper(int total_moves, int curr_moves, int[][] mat, boolean[] visited) {
        if (total_moves == 0)
            return 0;
        if (total_moves == 1)
            return 1;

        int ans = 0;
        visited[curr_moves] = true;
        for (int nextmoves = 1; nextmoves <= 9; nextmoves++) {
            if (visited[nextmoves] == false
                    && (mat[curr_moves][nextmoves] == 0 || visited[mat[curr_moves][nextmoves]])) {
                ans += solve01Helper(total_moves - 1, nextmoves, mat, visited);
            }
        }
        visited[curr_moves] = false;
        return ans;
    }
}