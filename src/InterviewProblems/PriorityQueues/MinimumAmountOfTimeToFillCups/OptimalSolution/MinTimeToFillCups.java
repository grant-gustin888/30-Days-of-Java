package InterviewProblems.PriorityQueues.MinimumAmountOfTimeToFillCups.OptimalSolution;

public class MinTimeToFillCups {

    // source: https://leetcode.com/problems/minimum-amount-of-time-to-fill-cups/discuss/2261394/JavaC%2B%2BPython-max(max(A)-(sum(A)-%2B-1)-2)
    // and https://leetcode.com/problems/minimum-amount-of-time-to-fill-cups/discuss/2294206/Rigorous-proof-of-the-correct-strategy-and-the-O(1)-formula-solution

    // Let n = the length of the amounts array.
    //
    // Time: O(1)
    // --> We only need to iterate through 3 elements in the amounts array.
    // Space: O(1)
    // --> We don't need any additional data structures.

    public static void main(String[] args) {
        MinTimeToFillCups minTimeToFillCups = new MinTimeToFillCups();

        // Input: amount = [5, 0, 0]
        // Output: 5
        // Explanation: Every second, we fill up a cold cup.
        int[] amount1 = {5, 0, 0};
        int minTime1 = minTimeToFillCups.fillCups(amount1);
        System.out.println(minTime1);  // 5

        // Input: amount = [1, 4, 2]
        // Output: 4
        // Explanation: One way to fill up the cups is:
        // Second 1: Fill up a cold cup and a warm cup.
        // Second 2: Fill up a warm cup and a hot cup.
        // Second 3: Fill up a warm cup and a hot cup.
        // Second 4: Fill up a warm cup.
        // It can be proven that 4 is the minimum number of seconds needed.
        int[] amount2 = {1, 4, 2};
        int minTime2 = minTimeToFillCups.fillCups(amount2);
        System.out.println(minTime2);  // 4

        // Input: amount = [5, 4, 4]
        // Output: 7
        // Explanation: One way to fill up the cups is:
        // Second 1: Fill up a cold cup, and a hot cup.
        // Second 2: Fill up a cold cup, and a warm cup.
        // Second 3: Fill up a cold cup, and a warm cup.
        // Second 4: Fill up a warm cup, and a hot cup.
        // Second 5: Fill up a cold cup, and a hot cup.
        // Second 6: Fill up a cold cup, and a warm cup.
        // Second 7: Fill up a hot cup.
        // It can be proven that 7 is the minimum number of seconds needed.
        int[] amount3 = {5, 4, 4};
        int minTime3 = minTimeToFillCups.fillCups(amount3);
        System.out.println(minTime3);  // 7
    }

    /**
     * You have a water dispenser that can dispense cold, warm, and hot water.
     * Every second, you can either fill up 2 cups with different types of
     * water, or 1 cup of any type of water.
     *
     * You are given a 0-indexed integer array amount of length 3 where
     * amount[0], amount[1], and amount[2] denote the number of cold, warm,
     * and hot water cups you need to fill respectively.
     *
     * Return the minimum number of seconds needed to fill up all the cups.
     *
     * Preconditions:
     * - amount.length == 3.
     * - 0 <= amount[i] <= 100.
     */
    public int fillCups(int[] targetNumCups) {
        int maxNumCups = 0;
        int totalRequiredNumCups = 0;
        for (int targetNumCup : targetNumCups) {
            maxNumCups = Math.max(targetNumCup, maxNumCups);
            totalRequiredNumCups += targetNumCup;
        }

        // min time to fill all cups >= max(A) --> select 1 cup
        // min time to fill all cups >= ceil(sum(A) / 2) --> select up to 2 cups
        return Math.max(maxNumCups, (totalRequiredNumCups + 1) / 2);
    }
}
