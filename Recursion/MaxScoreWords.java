
/**
 * Problem Statement:
 * https://leetcode.com/problems/maximum-score-words-formed-by-letters/
 */
public class MaxScoreWords {

    public static void main(String[] args) {
        String[] words = { "dog", "cat", "dad", "good" };
        char[] letters = { 'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o' };
        int[] score = { 1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int max_score = maxScoreWords(words, letters, score);
        System.out.println(max_score);
    }

    public static int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] freq_map = new int[26];
        for (char ch : letters)
            freq_map[ch - 'a']++;
        return max_score(words, 0, freq_map, score);
    }

    private static int max_score(String[] words, int idx, int[] freq_map, int[] score) {
        if (idx >= words.length)
            return 0;

        int excluded = max_score(words, idx + 1, freq_map, score);

        String curr_word = words[idx];
        int curr_word_score = 0;
        int i = 0;
        while (i < curr_word.length()) {
            char ch = curr_word.charAt(i);
            if (freq_map[ch - 'a'] == 0) {
                increase_freq(freq_map, curr_word, --i);
                return excluded;
            }
            curr_word_score += score[ch - 'a'];
            freq_map[ch - 'a']--;
            i++;
        }
        int included = max_score(words, idx + 1, freq_map, score) + curr_word_score;
        increase_freq(freq_map, curr_word, --i);
        return Math.max(excluded, included);
    }

    private static void increase_freq(int[] freq_map, String word, int idx) {
        while (idx >= 0) {
            freq_map[word.charAt(idx) - 'a']++;
            idx--;
        }
    }
}
