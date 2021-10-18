/**
 * Problem Statement: https://leetcode.com/problems/n-queens/
 */

public class NQueens {
    public static void main(String[] args) {
        int n = 6;
        boolean[][] board = new boolean[n][n];
        // nQueen01(board, 0, n, "");
        // nQueen02(board, 0, n, "");
        // nQueen03(board, 0, n, "", new boolean[n], new boolean[n], new boolean[2 * n -
        // 1], new boolean[2 * n - 1], n);
        nQueen04(board, 0, n, "", new boolean[n], new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], n);
    }

    /* METHOD 4 FOR SOLVING N QUEENS PROBLEM */
    private static void nQueen04(boolean[][] board, int i, int total_queens, String ans, boolean[] row, boolean[] col,
            boolean[] diag1, boolean[] diag2, int N) {
        if (total_queens == 0) {
            System.out.println(ans);
            return;
        }
        for (int j = 0; j < board[0].length; j++) {
            if (!row[i] && !col[j] && !diag1[i + j] && !diag2[i - j + N - 1]) {
                row[i] = col[j] = diag1[i + j] = diag2[i - j + N - 1] = true;
                nQueen04(board, i + 1, total_queens - 1, ans + "r" + i + "c" + j + "\n", row, col, diag1, diag2, N);
                row[i] = col[j] = diag1[i + j] = diag2[i - j + N - 1] = false;
            }
        }
    }

    /* METHOD 3 FOR SOLVING N QUEENS PROBLEM */
    private static void nQueen03(boolean[][] board, int boxno, int total_queens, String ans, boolean[] row,
            boolean[] col, boolean[] diag1, boolean[] diag2, int N) {
        if (total_queens == 0) {
            System.out.println(ans);
            return;
        }
        for (int i = boxno; i < board.length * board[0].length; i++) {
            int r = i / board[0].length;
            int c = i % board[0].length;
            if (!row[r] && !col[c] && !diag1[r + c] && !diag2[r - c + N - 1]) {
                row[r] = col[c] = diag1[r + c] = diag2[r - c + N - 1] = true;
                nQueen03(board, i + 1, total_queens - 1, ans + "r" + r + "c" + c + "\n", row, col, diag1, diag2, N);
                row[r] = col[c] = diag1[r + c] = diag2[r - c + N - 1] = false;
            }
        }
    }

    /* METHOD 2 FOR SOLVING N QUEENS PROBLEM */
    private static void nQueen02(boolean[][] board, int boxno, int total_queens, String ans) {
        if (total_queens == 0) {
            System.out.println(ans);
            return;
        }
        for (int i = boxno; i < board.length * board[0].length; i++) {
            int r = i / board[0].length;
            int c = i % board[0].length;
            if (isValidPlace02(board, r, c)) {
                board[r][c] = true;
                nQueen02(board, i + 1, total_queens - 1, ans + "r" + r + "c" + c + "\n");
                board[r][c] = false;
            }
        }
    }

    private static boolean isValidPlace02(boolean[][] board, int r, int c) {
        int[][] dir = { { -1, 0 }, { 0, -1 }, { -1, -1 }, { -1, 1 } };
        for (int k = 1; k < board.length; k++) {
            for (int i = 0; i < dir.length; i++) {
                int nr = r + dir[i][0] * k;
                int nc = c + dir[i][1] * k;
                if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length)
                    if (board[nr][nc])
                        return false;
            }
        }
        return true;
    }

    /* METHOD 1 FOR SOLVING N QUEENS PROBLEM */
    private static void nQueen01(boolean[][] board, int row, int total_queens, String ans) {
        if (total_queens == 0) {
            System.out.println(ans);
            return;
        }
        for (int col = 0; col < board[0].length; col++) {
            if (isValidPlace01(board, row, col)) {
                board[row][col] = true;
                nQueen01(board, row + 1, total_queens - 1, ans + "r" + row + "c" + col + "\n");
                board[row][col] = false;
            }
        }
    }

    private static boolean isValidPlace01(boolean[][] board, int row, int col) {
        // COLUMN CHECK
        for (int i = row, j = col; i >= 0; i--) {
            if (board[i][j])
                return false;
        }
        // Diagonal 1 check
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j])
                return false;
        }
        // Diagonal 2 check
        for (int i = row, j = col; i >= 0 && j < board[0].length; i--, j++) {
            if (board[i][j])
                return false;
        }
        return true;
    }

}