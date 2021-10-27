
/**
 * 1) Problem Statement: https://leetcode.com/problems/permutations/
 * 2) Problem Statement: https://leetcode.com/problems/permutations-ii/
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Permutation {
    public static void main(String[] args) {
        Permutation p = new Permutation();
        int[] nums = { 1, 1, 3 };
        // System.out.println(p.permute01(nums));
        // permute01String("abc");
    }

    /**
     * When Input is in String Format && recursion is return type
     */
    public static void permute01String(String str) {
        System.out.println(permute01StringHelper(str, 0));
    }

    private static ArrayList<String> permute01StringHelper(String str, int idx) {
        if (idx == str.length() - 1)
            return new ArrayList<>(Arrays.asList(str.charAt(idx) + ""));
        ArrayList<String> recAns = permute01StringHelper(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>();
        char ch = str.charAt(idx);
        for (String ele : recAns) {
            for (int i = 0; i <= ele.length(); i++) {
                String s = ele.substring(0, i) + ch + ele.substring(i);
                myAns.add(s);
            }
        }
        return myAns;
    }

    /**
     * When Input is in String Format && recursion is void type
     */
    public static void permute0101String(String str) {
        permute0101StringHelper(str, "");
    }

    private static void permute0101StringHelper(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            String ros = str.substring(0, i) + str.substring(i + 1);
            permute0101StringHelper(ros, ans + str.charAt(i));
        }
    }

    /**
     * Problem Statement: https://leetcode.com/problems/permutations/ => When Input
     * is in List Format && recursion is return type
     */
    public List<List<Integer>> permute01(int[] nums) {
        return permute01Helper(nums, 0);
    }

    private List<List<Integer>> permute01Helper(int[] nums, int idx) {
        List<List<Integer>> ans = new LinkedList<>();

        if (idx >= nums.length)
            return ans;
        else if (idx == nums.length - 1) {
            List<Integer> smallAns = new LinkedList<>(Arrays.asList(nums[idx]));
            ans.add(smallAns);
            return ans;
        }

        List<List<Integer>> recAns = permute01Helper(nums, idx + 1);
        for (List<Integer> list : recAns) {
            for (int i = 0; i <= list.size(); i++) {
                List<Integer> li = new LinkedList<>(list);
                li.add(i, nums[idx]);
                ans.add(li);
            }
        }
        return ans;
    }

    /**
     * Problem Statement: https://leetcode.com/problems/permutations/ => When Input
     * is in List Format && recursion is void type
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        solve(nums, 0, new boolean[nums.length], new ArrayList<>(), ans);
        return ans;
    }

    public void solve(int[] nums, int idx, boolean[] used, List<Integer> asf, List<List<Integer>> ans) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(asf));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == false) {
                used[i] = true;
                asf.add(nums[i]);
                solve(nums, idx + 1, used, asf, ans);
                asf.remove(asf.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * Problem Statement: https://leetcode.com/problems/permutations-ii/ => When
     * Input is in List Format && recursion is void type
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permuteHelper(nums, 0, ans);
        return ans;
    }

    private void permuteHelper(int[] nums, int idx, List<List<Integer>> ans) {
        if (idx >= nums.length) {
            ans.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        boolean[] used = new boolean[21]; // [-10 <= 10]
        for (int i = idx; i < nums.length; i++) {
            if (!used[nums[i] + 10]) {
                used[nums[i] + 10] = true;
                swap(nums, idx, i);
                permuteHelper(nums, idx + 1, ans);
                swap(nums, idx, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
