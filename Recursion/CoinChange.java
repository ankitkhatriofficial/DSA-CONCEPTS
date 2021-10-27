
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = { 2, 3, 5, 7 };
        int target = 10;
        // System.out.println("Total answer = " + permuteWithInfiniteCoins(coins,
        // target));
        // System.out.println("Total answer = " + CombinationWithInfiniteCoins(coins,
        // target));
        System.out.println("Total answer = " + PermutationWithSingleCoins(coins, target));
    }

    /**
     * Combination of Single coins Supply
     */
    private static int PermutationWithSingleCoins(int[] coins, int target) {
        return helper04(coins, target, "", new boolean[coins.length]);
    }

    private static int helper04(int[] coins, int target, String ans, boolean[] visited) {
        if (target == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= target && !visited[i]) {
                visited[i] = true;
                count += helper04(coins, target - coins[i], ans + coins[i], visited);
                visited[i] = false;
            }
        }
        return count;
    }

    /**
     * Combination of Single coins Supply
     */
    private static int CombinationWithSingleCoins(int[] coins, int target) {
        return helper03(coins, target, 0, "");
    }

    private static int helper03(int[] coins, int target, int idx, String ans) {
        if (target == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (coins[i] <= target)
                count += helper03(coins, target - coins[idx], idx + 1, ans + coins[idx]);
        }
        return count;

        /** way 2 To solve this problem **/
        /*
         * if(target == 0){ System.out.println(ans); return 1; }else if(idx >=
         * coins.length) return 0;
         * 
         * int count = 0; count += helper03(coins, target - coins[idx], idx + 1, ans +
         * coins[idx]); count += helper03(coins, target, idx + 1, ans); return count;
         */
    }

    /**
     * Combination of Infinite coins Supply
     */
    private static int CombinationWithInfiniteCoins(int[] coins, int target) {
        return helper02(coins, target, 0, "");
    }

    private static int helper02(int[] coins, int target, int idx, String ans) {
        if (target < 0)
            return 0;
        if (target == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (coins[i] <= target) {
                count += helper02(coins, target - coins[i], i, ans + coins[i]);
            }
        }
        return count;
    }

    /**
     * Permutation of Infinite coins Supply
     */
    private static int permuteWithInfiniteCoins(int[] coins, int target) {
        return helper01(coins, target, "");
    }

    private static int helper01(int[] coins, int target, String ans) {
        if (target < 0)
            return 0;
        if (target == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= target) {
                count += helper01(coins, target - coins[i], ans + coins[i]);
            }
        }
        return count;
    }
}