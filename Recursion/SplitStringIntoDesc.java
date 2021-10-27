
/**
 * Problem Statement:
 * https://leetcode.com/problems/splitting-a-string-into-descending-consecutive-values/
 */
public class SplitStringIntoDesc {
    public static void main(String[] args) {
        String str1 = "050043";
        String str2 = "9080701";
        String str3 = "10009998";
        System.out.println(splitString(str1));
        System.out.println(splitString(str2));
        System.out.println(splitString(str3));
    }

    public static boolean splitString(String s) {
        boolean res = false;
        for (int i = 0; i < s.length() - 1; i++) {
            long num = StringToNum(s.substring(0, i + 1));
            res = res || canFormDesc(num, s, i + 1);
        }
        return res;
    }

    private static boolean canFormDesc(long prev_num, String s, int idx) {
        if (idx >= s.length())
            return true;
        boolean res = false;
        for (int i = idx; i < s.length(); i++) {
            long curr_num = StringToNum(s.substring(idx, i + 1));
            if (curr_num > prev_num)
                return false;
            if (prev_num - curr_num == 1) {
                res = res || canFormDesc(curr_num, s, i + 1);
                if (res)
                    return res;
            }
        }
        return res;
    }

    private static long StringToNum(String str) {
        long num = 0;
        for (int i = 0; i < str.length(); i++)
            num = num * 10 + (str.charAt(i) - '0');
        return num;
    }
}
