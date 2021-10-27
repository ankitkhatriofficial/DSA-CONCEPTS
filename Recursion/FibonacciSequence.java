
import java.util.ArrayList;
import java.util.List;

public class FibonacciSequence {
    public static void main(String[] args) {
        String str = "123456579";
        List<Integer> ans = new ArrayList<>();
        System.out.println(solve01(str, 0, ans));
        System.out.println(ans);
    }

    private static boolean solve01(String num, int idx, List<Integer> ans) {
        if (num.length() < 3)
            return false;

        boolean res = false;
        for (int i = idx; i < num.length(); i++) {
            long f1 = Long.parseLong(num.substring(0, i + 1));
            if ((num.charAt(0) == '0' && i != 0) || f1 > Integer.MAX_VALUE)
                break;

            for (int j = i + 1; j < num.length() - 1; j++) {
                long f2 = Long.parseLong(num.substring(i + 1, j + 1));
                if ((num.charAt(i + 1) == '0' && j != i + 1) || f2 > Integer.MAX_VALUE)
                    break;
                ans.add((int) f1);
                ans.add((int) f2);
                res = res || solve01Helper(num, j + 1, (int) f1, (int) f2, ans);
                if (res)
                    return res;
                ans.remove(ans.size() - 1);
                ans.remove(ans.size() - 1);
            }
        }
        return res;
    }

    private static boolean solve01Helper(String num, int idx, int f1, int f2, List<Integer> ans) {
        if (idx >= num.length())
            return true;

        boolean res = false;
        for (int i = idx; i < num.length(); i++) {
            long f3 = Long.parseLong(num.substring(idx, i + 1));
            if (num.charAt(idx) == '0' && i != idx)
                break;

            if (f3 > Integer.MAX_VALUE)
                return false;

            if (f1 + f2 == f3) {
                ans.add((int) f3);
                res = res || solve01Helper(num, i + 1, f2, (int) f3, ans);
                if (res)
                    return res;
                ans.remove(ans.size() - 1);
            }

        }
        return res;
    }

}