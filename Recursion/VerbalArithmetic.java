import java.util.Arrays;

public class VerbalArithmetic {
    public static void main(String[] args) {
        // String[] words = { "LEET", "CODE" };
        // String result = "POINT";
        String[] words = { "CBA", "CBA", "CBA", "CBA", "CBA" };
        String result = "EDD";
        System.out.println(solve01(words, result));
    }

    public static boolean solve01(String[] words, String result) {
        StringBuilder sb = new StringBuilder();
        int[] map = new int[26];
        for (String word : words) {
            for (char ch : word.toCharArray())
                map[ch - 'A']++;
        }
        for (char ch : result.toCharArray())
            map[ch - 'A']++;

        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0)
                sb.append((char) (i + 'A'));
        }
        int[] charMap = new int[26];
        char[] numMap = new char[10];
        Arrays.fill(charMap, -1);
        Arrays.fill(numMap, '.');
        return solve01Helper(words, result, sb.toString(), 0, charMap, numMap);
    }

    public static boolean solve01Helper(String[] words, String result, String unique, int idx, int[] charMap,
            char[] numMap) {
        if (idx >= unique.length()) {
            int sum = 0;
            for (String word : words) {
                sum += generateNumber(word, charMap);
            }
            if (sum == generateNumber(result, charMap))
                return true;
            return false;
        }
        boolean res = false;
        char ch = unique.charAt(idx);
        for (int num = 0; num <= 9; num++) {
            if (numMap[num] == '.' && num == 0 && check(words, result, charMap)) {
                charMap[ch - 'A'] = num;
                numMap[num] = ch;
                res = res || solve01Helper(words, result, unique, idx + 1, charMap, numMap);
                charMap[ch - 'A'] = -1;
                numMap[num] = '.';
            }
        }
        return res;
    }

    private static boolean check(String[] words, String result, int[] charMap) {
        for (String word : words) {
            if (charMap[word.charAt(0) - 'A'] == 0 && word.length() > 1)
                return false;
        }
        return true;
    }

    private static int generateNumber(String word, int[] charMap) {
        int number = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            number = number * 10 + charMap[ch - 'A'];
        }
        return number;
    }
}
