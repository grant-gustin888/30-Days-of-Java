package InterviewProblems.PriorityQueues.MinimumAmountOfTimeToFillCups.OriginalSolution;

import java.util.PriorityQueue;

public class MinTimeToFillCups {

    // Let n = the length of the amounts array.
    //
    // Time: O(n log n) TODO: I'm not sure what the runtime should be here...
    // --> O(n log n) to populate all n elements in the max priority queue
    // --> O(n log n) to process all n elements of the max priority queue.
    // Space: O(1)
    // --> We store up to 3 integers in our max priority queue and our
    // temporary max priority queue.

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
        int numSecondsElapsed = 0;

        // A max priority queue of the current number of cups so far...
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int targetNumCup : targetNumCups) {
            if (targetNumCup >= 1) {
                maxPriorityQueue.add(targetNumCup);
            }
        }

        // A temporary priority queue...
        PriorityQueue<Integer> tempMaxPriorityQueue = new PriorityQueue<>((a, b) -> b - a);

        while (!maxPriorityQueue.isEmpty()) {
            numSecondsElapsed++;

            // fill up two most unfilled cups...
            for (int numCupsFilled = 0; numCupsFilled < 2; numCupsFilled++) {
                if (maxPriorityQueue.isEmpty()) {
                    break;
                } else {
                    int numCupsLeft = maxPriorityQueue.poll();
                    numCupsLeft--;
                    if (numCupsLeft >= 1) {
                        tempMaxPriorityQueue.add(numCupsLeft);
                    }
                }
            }

            // Place all non-zero cups remaining counts back into the main max priority queue.
            while (!tempMaxPriorityQueue.isEmpty()) {
                int numCupsLeft = tempMaxPriorityQueue.poll();
                maxPriorityQueue.add(numCupsLeft);
            }
        }

        return numSecondsElapsed;
    }
}
