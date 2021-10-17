public class FriendsPairing {
    public static void main(String[] args) {
        int N = 4;
        solve01(N);
    }

    private static void solve01(int n) {
        boolean[] used = new boolean[n + 1];
        int count = solve01Helper(n, used, 1, "");
        System.out.println("Total count = " + count);
    }

    private static int solve01Helper(int n, boolean[] used, int num, String ans) {
        if (num > n) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        if (!used[num]) {
            used[num] = true;
            count += solve01Helper(n, used, num + 1, ans + "(" + num + ")");
            for (int nextPair = num + 1; nextPair <= n; nextPair++) {
                if (!used[nextPair]) {
                    used[nextPair] = true;
                    count += solve01Helper(n, used, num + 1, ans + "(" + num + "" + nextPair + ")");
                    used[nextPair] = false;
                }
            }
            used[num] = false;
        } else
            count += solve01Helper(n, used, num + 1, ans);
        return count;
    }
}