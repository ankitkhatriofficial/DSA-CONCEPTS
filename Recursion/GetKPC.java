
import java.util.ArrayList;
import java.util.List;

/**
 * Problem Statement:
 * https://www.geeksforgeeks.org/find-possible-words-phone-digits/
 */

public class GetKPC {
    public static void main(String[] args) {
        int[] nums = { 6, 8, 4, 8, 9, 6, 7 };
        getKPC01(nums);
    }

    private static char[][] letters = { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' },
            { 'j', 'k', 'l' }, { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

    private static void getKPC01(int[] nums) {
        List<String> list = getKPC01Helper(nums, 0);
        System.out.println(list);
    }

    private static List<String> getKPC01Helper(int[] nums, int idx) {
        List<String> ans = new ArrayList<>();
        if (idx >= nums.length)
            return ans;
        if (idx == nums.length - 1) {
            for (char ch : letters[nums[idx]])
                ans.add(ch + "");
            return ans;
        }

        List<String> pans = getKPC01Helper(nums, idx + 1);
        for (char ch : letters[nums[idx]]) {
            for (String str : pans)
                ans.add(ch + str);
        }
        return ans;
    }
}
