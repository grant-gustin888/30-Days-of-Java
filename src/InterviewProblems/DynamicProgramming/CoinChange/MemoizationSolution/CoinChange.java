package InterviewProblems.DynamicProgramming.CoinChange.MemoizationSolution;

import java.util.Arrays;

public class CoinChange {

    // Let c = number of unique coin denominations
    // Let n = the target coin amount.
    //
    // Time: O(c ^ n)
    // --> We must make c recursive calls for potentially n level if one of the coin denominations is 1
    // to find the minimum number of coins needed to make the target amount.
    // Space: O(n)
    // --> We need up to n stack frames if the coin denomination is 1 to
    // find the minimum number of coins needed to make the target amount.

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
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1); // Initialize memoization array with -1
        int coinsNeeded = coinChangeRecursive(coins, amount, memo);
        return (coinsNeeded != Integer.MAX_VALUE) ? coinsNeeded : -1;
    }

    public int coinChangeRecursive(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }

        if (memo[amount] != -1) {
            return memo[amount];
        }

        int minCoinsNeeded = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount - coin >= 0) {
                int coinsNeeded = coinChangeRecursive(coins, amount - coin, memo);
                if (coinsNeeded != Integer.MAX_VALUE) {
                    minCoinsNeeded = Math.min(minCoinsNeeded, coinsNeeded + 1);
                }
            }
        }

        memo[amount] = minCoinsNeeded; // Memoize the minimum number of coins needed to make the target amount
        return minCoinsNeeded;
    }
}
