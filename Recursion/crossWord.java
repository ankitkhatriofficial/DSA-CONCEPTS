import java.util.Arrays;

public class crossWord {
    public static void main(String[] args) {
        char[][] board = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

        String[] words = { "agra", "norway", "england", "gwalior" };
        crossWord01(board, words);
    }

    private static void display(char[][] board) {
        for (char[] arr : board)
            System.out.println(Arrays.toString(arr));
    }

    private static void crossWord01(char[][] board, String[] words) {
        crossWordSolver01(board, words, 0);
    }

    private static void crossWordSolver01(char[][] board, String[] words, int idx) {
        if (idx == words.length) {
            display(board);
            return;
        }
        String curr_word = words[idx];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '-') {
                    if (canPlaceVertically(board, i, j, curr_word)) {
                        boolean[] wePlaced = placeWordVertically(board, i, j, curr_word);
                        crossWordSolver01(board, words, idx + 1);
                        unplaceWordVertically(board, i, j, curr_word, wePlaced);
                    }
                    if (canPlaceHorizontally(board, i, j, curr_word)) {
                        boolean[] wePlaced = placeWordHorizontally(board, i, j, curr_word);
                        crossWordSolver01(board, words, idx + 1);
                        unplaceWordHorizontally(board, i, j, curr_word, wePlaced);
                    }
                }
            }
        }
    }

    public static boolean canPlaceVertically(char[][] board, int i, int j, String word) {
        if (i - 1 >= 0 && board[i - 1][j] != '+') {
            return false;
        }

        if (i + word.length() < board[0].length && board[i + word.length()][j] != '+') {
            return false;
        }
        for (int k = 0; k < word.length(); k++) {
            if (board[i + k][j] == '+') {
                return false;
            }
            if (board[i + k][j] == '-' || board[i + k][j] == word.charAt(k)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeWordVertically(char[][] board, int i, int j, String word) {
        boolean[] pbm = new boolean[word.length()];
        for (int k = 0; k < word.length(); k++) {
            if (board[i + k][j] == '-') {
                pbm[k] = true;
                board[i + k][j] = word.charAt(k);
            }
        }
        return pbm;
    }

    public static boolean[] placeWordHorizontally(char[][] board, int i, int j, String word) {
        boolean[] pbm = new boolean[word.length()];
        for (int k = 0; k < word.length(); k++) {
            if (board[i][j + k] == '-') {
                pbm[k] = true;
                board[i][j + k] = word.charAt(k);
            }
        }
        return pbm;
    }

    public static void unplaceWordVertically(char[][] board, int i, int j, String word, boolean[] pbm) {
        for (int k = 0; k < word.length(); k++) {
            if (pbm[k] == true) {
                board[i + k][j] = '-';
            }
        }
    }

    public static boolean canPlaceHorizontally(char[][] board, int i, int j, String word) {
        if (j - 1 >= 0 && board[i][j - 1] != '+') {
            return false;
        }

        if (j + word.length() < board.length && board[i][j + word.length()] != '+') {
            return false;
        }
        for (int k = 0; k < word.length(); k++) {
            if (board[i][j + k] == '+') {
                return false;
            }
            if (board[i][j + k] == '-' || board[i][j + k] == word.charAt(k)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void unplaceWordHorizontally(char[][] board, int i, int j, String word, boolean[] pbm) {
        for (int k = 0; k < word.length(); k++) {
            if (pbm[k] == true) {
                board[i][j + k] = '-';
            }
        }
    }

    // private static boolean canPlaceVertically(char[][] board, int i, int j,
    // String word) {
    // if (i - 1 >= 0 && board[i - 1][j] != '+')
    // return false;
    // if (i + word.length() < board[0].length && board[i + word.length()][j] !=
    // '+')
    // return false;
    // for (int k = 0; k < word.length(); k++) {
    // if (board[i + k][j] == '+' || board[i + k][j] != '-' || board[i + k][j] !=
    // word.charAt(k))
    // return false;
    // }
    // return true;
    // }

    // private static boolean[] placeVertically(char[][] board, int i, int j, String
    // word) {
    // boolean[] wePlaced = new boolean[word.length()];
    // for (int k = 0; k < word.length(); k++) {
    // if (board[i + k][j] == '-') {
    // wePlaced[k] = true;
    // board[i + k][j] = word.charAt(k);
    // }
    // }
    // return wePlaced;
    // }

    // private static void unplaceVertically(char[][] board, int i, int j, String
    // word, boolean[] wePlaced) {
    // for (int k = 0; k < word.length(); k++) {
    // if (wePlaced[k])
    // board[i + k][j] = '-';
    // }
    // }

    // private static boolean canPlaceHorizontally(char[][] board, int i, int j,
    // String word) {
    // if (j - 1 >= 0 && board[i][j - 1] != '+')
    // return false;
    // if (j + word.length() < board[0].length && board[i][j + word.length()] !=
    // '+')
    // return false;
    // for (int k = 0; k < word.length(); k++) {
    // if (board[i][j + k] == '+' || board[i][j + k] != '-' || board[i][j + k] !=
    // word.charAt(k))
    // return false;
    // }
    // return true;
    // }

    // private static boolean[] placeHorizontally(char[][] board, int i, int j,
    // String word) {
    // boolean[] wePlaced = new boolean[word.length()];
    // for (int k = 0; k < word.length(); k++) {
    // if (board[i][j + k] == '-') {
    // wePlaced[k] = true;
    // board[i][j + k] = word.charAt(k);
    // }
    // }
    // return wePlaced;
    // }

    // private static void unplaceHorizontally(char[][] board, int i, int j, String
    // word, boolean[] wePlaced) {
    // for (int k = 0; k < word.length(); k++) {
    // if (wePlaced[k])
    // board[i][j + k] = '-';
    // }
    // }
}