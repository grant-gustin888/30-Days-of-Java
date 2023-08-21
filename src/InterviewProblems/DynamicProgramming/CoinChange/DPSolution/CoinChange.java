package InterviewProblems.DynamicProgramming.CoinChange.DPSolution;

import java.util.Arrays;

public class CoinChange {

    // Let c = number of unique coin denominations
    // Let n = the target coin amount.
    //
    // Time: O(c * n)
    // --> For every coin amount, we must iterate through each coin denomination to find the
    // minimum number of coins needed to make that amount.
    // Space: O(n)
    // --> We must allocate an array of size O(n): one for each coin amount
    // from 0 to targetCoinAmount.

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();

        // Example 1:
        // Inputs: coins = [2], amount = 2
        // Output: 1
        int[] coinDenominations1 = {2};
        long coinChange1 = coinChange.coinChange(coinDenominations1, 2);
        System.out.println(coinChange1);  // 1

        // Example 2:
        // Inputs: coins = [2], amount = 3
        // Output: -1
        int[] coinDenominations2 = {2};
        long coinChange2 = coinChange.coinChange(coinDenominations2, 3);
        System.out.println(coinChange2);  // -1

        // Example 3:
        // Inputs: coins = [2, 4], amount = 4
        // Output: 1
        int[] coinDenominations3 = {2, 4};
        long coinChange3 = coinChange.coinChange(coinDenominations3, 4);
        System.out.println(coinChange3);  // 1

        // Example 4:
        // Inputs: coins = [1, 2, 5], amount = 11
        // Output: 4
        // Explanation: 11 = 5 + 5 + 1
        int[] coinDenominations4 = {1, 2, 5};
        long coinChange4 = coinChange.coinChange(coinDenominations4, 11);
        System.out.println(coinChange4);  // 3
    }

    /**
     * You are given coins of different denominations and a total amount of money amount.
     * Write a function to compute the fewest number of coins that you need to make up
     * that amount.
     *
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     *
     * You may assume that you have an infinite number of each kind of coin.
     *
     * Preconditions:
     * - 1 <= coins.length <= 12
     * - 1 <= coins[i] <= 2 ^ 31 - 1
     * - 0 <= amount <= 10 ^ 4
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);  // set default value to targetCoinAmount + 1

        dp[0] = 0;  // 0 ways to make 0 coins
        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (a - coin >= 0) {
                    dp[a] = Math.min(dp[a], 1 + dp[a - coin]);
                }
            }
        }

        if (dp[amount] != amount + 1) {
            return dp[amount];
        } else {
            return -1;
        }
    }
}
