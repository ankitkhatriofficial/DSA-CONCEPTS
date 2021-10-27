
import java.util.Arrays;

/**
 * Problem Statement: https://leetcode.com/problems/sudoku-solver/
 */

public class Suduko {
    public static void main(String[] args) {
        char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        // sudukoSolver01(board, 0);
        sudukoSolver02(board);
    }

    /* METHOD 2 FOR SOLVING SUDUKO */
    private static void sudukoSolver02(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] matrix = new boolean[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num - 1] = col[j][num - 1] = true;
                    matrix[((i / 3) * 3) + ((num - 1) / 3)][((j / 3) * 3) + ((num - 1) % 3)] = true;
                }
            }
        }

        sudukoSolver02Helper(board, 0, row, col, matrix);
    }

    private static void sudukoSolver02Helper(char[][] board, int idx, boolean[][] row, boolean[][] col,
            boolean[][] matrix) {
        if (idx == 81) {
            display(board);
            return;
        }
        int r = idx / board[0].length;
        int c = idx % board[0].length;
        if (board[r][c] != '.')
            sudukoSolver02Helper(board, idx + 1, row, col, matrix);
        else {
            int sr = (r / 3) * 3;
            int sc = (c / 3) * 3;
            for (int num = 1; num <= 9; num++) {
                int obr = (num - 1) / 3;
                int obc = (num - 1) % 3;
                if (!row[r][num - 1] && !col[c][num - 1] && !matrix[sr + obr][sc + obc]) {
                    row[r][num - 1] = col[c][num - 1] = matrix[sr + obr][sc + obc] = true;
                    board[r][c] = (char) (num + '0');
                    sudukoSolver02Helper(board, idx + 1, row, col, matrix);
                    board[r][c] = '.';
                    row[r][num - 1] = col[c][num - 1] = matrix[sr + obr][sc + obc] = false;
                }
            }
        }
    }

    /* METHOD 1 FOR SOLVING SUDUKO */
    private static void sudukoSolver01(char[][] board, int idx) {
        if (idx == 81) {
            display(board);
            return;
        }
        int r = idx / board[0].length;
        int c = idx % board[0].length;
        if (board[r][c] != '.')
            sudukoSolver01(board, idx + 1);
        else {
            for (int num = 1; num <= 9; num++) {
                if (isValidPlace01(board, r, c, num)) {
                    board[r][c] = (char) (num + '0');
                    sudukoSolver01(board, idx + 1);
                    board[r][c] = '.';
                }
            }
        }
    }

    private static boolean isValidPlace01(char[][] board, int r, int c, int num) {
        // row check
        for (int i = r, j = 0; j < board[0].length; j++) {
            if (board[i][j] - '0' == num)
                return false;
        }
        // column check
        for (int i = 0, j = c; i < board.length; i++) {
            if (board[i][j] - '0' == num)
                return false;
        }
        // innner submatrix check
        int sr = (r / 3) * 3;
        int sc = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[sr + i][sc + j] - '0' == num)
                    return false;
            }
        }
        return true;
    }

    /* UTILITY FUNCTION TO PRINT BOARD */
    private static void display(char[][] board) {
        for (char[] ch : board)
            System.out.println(Arrays.toString(ch));
        System.out.println();
    }
}