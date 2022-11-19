package InterviewProblems.MediumQuestions.BinarySearch.KokoEatingBananas.OriginalSolution;

public class KokoEatingBananas {

    // Let n = the length of the piles array.
    //
    // Time: O(n log n)
    // --> O(n) to find the maximum element in the array.
    // --> O(log n) iterations to find the minimum eating speed using binary search
    // and O(n) per iteration to compare the total eating time at the current minimum speed against h.
    //
    // Space: O(1)
    // --> We don't require any additional data structures: we only use a few variables
    // like minEatingSpeed, maxEatingSpeed, middleEatingSpeed, and totalEatingTime.

    public static void main(String[] args) {
        KokoEatingBananas kokoEatingBananas = new KokoEatingBananas();

        // Input: piles = [3, 6, 7, 11], h = 8
        // Output: 4
        int[] piles1 = {3, 6, 7, 11};
        int minEatingSpeed1 = kokoEatingBananas.minEatingSpeed(piles1, 8);
        System.out.println(minEatingSpeed1);  // 4

        // Input: piles = [30, 11, 23, 4, 20], h = 5
        // Output: 30
        int[] piles2 = {30, 11, 23, 4, 20};
        int minEatingSpeed2 = kokoEatingBananas.minEatingSpeed(piles2, 5);
        System.out.println(minEatingSpeed2);  // 30

        // Input: piles = [30, 11, 23, 4, 20], h = 6
        // Output: 23
        int[] piles3 = {30, 11, 23, 4, 20};
        int minEatingSpeed3 = kokoEatingBananas.minEatingSpeed(piles3, 6);
        System.out.println(minEatingSpeed3);  // 23

        // Input: piles = [312884470], h = 312884469
        // Output: 2
        int[] piles4 = {312_884_470};
        int minEatingSpeed4 = kokoEatingBananas.minEatingSpeed(piles4, 312_884_469);
        System.out.println(minEatingSpeed4);  // 2

        // Input: piles = [312884470], h = 312884470
        // Output: 2
        int[] piles5 = {312_884_470};
        int minEatingSpeed5 = kokoEatingBananas.minEatingSpeed(piles5, 312_884_470);
        System.out.println(minEatingSpeed5);  // 1

        // Input: piles = [805306368, 805306368, 805306368], h = 1000000000
        // Output: 3
        int[] piles6 = {805_306_368, 805_306_368, 805_306_368};
        int minEatingSpeed6 = kokoEatingBananas.minEatingSpeed(piles6, 1_000_000_000);
        System.out.println(minEatingSpeed6);  // 3
    }

    /**
     * Koko loves to eat bananas. There are n piles of bananas, the ith pile
     * has piles[i] bananas. The guards have gone and will come back in h hours.
     *
     * Koko can decide her bananas-per-hour eating speed of k. Each hour,
     * she chooses some pile of bananas and eats k bananas from that pile.
     * If the pile has less than k bananas, she eats all of them instead and
     * will not eat any more bananas during this hour.
     *
     * Koko likes to eat slowly but still wants to finish eating all the
     * bananas before the guards return.
     *
     * Return the minimum integer k such that she can eat all the bananas
     * within h hours.
     *
     * Preconditions:
     * - 1 <= piles.length <= 10 ^ 4.
     * - piles.length <= h <= 10 ^ 9.
     * - 1 <= piles[i] <= 10 ^ 9.
     */
    public int minEatingSpeed(int[] piles, int h) {
        // 3rd try:
        int minEatingSpeed = 1;
        int maxEatingSpeed = max(piles);
        while (minEatingSpeed <= maxEatingSpeed) {
            int middleEatingSpeed = (minEatingSpeed + maxEatingSpeed) / 2;
            int totalEatingTime = getTotalEatingTime(piles, middleEatingSpeed);
            if (totalEatingTime == h) {  // eating speed just right
                // slow down maximum eating speed.
                maxEatingSpeed = middleEatingSpeed - 1;
            } else if (totalEatingTime < h) {  // eating speed too fast
                // slow down maximum eating speed.
                maxEatingSpeed = middleEatingSpeed - 1;
            } else {  // totalEatingTime > h, eating speed too slow
                // speed up minimum eating speed.
                minEatingSpeed = middleEatingSpeed + 1;
            }
        }
        return minEatingSpeed;

        // 2nd try:
        // int minEatingSpeed = 1;
        // int maxEatingSpeed = max(piles);
        // while (minEatingSpeed <= maxEatingSpeed) {
        //     int middleEatingSpeed = (minEatingSpeed + maxEatingSpeed) / 2;
        //     int totalEatingTime = getTotalEatingTime(piles, middleEatingSpeed);
        //     if (totalEatingTime == h) {  // eating speed just right
        //        // slow down maximum eating speed.
        //        maxEatingSpeed = middleEatingSpeed - 1;
        //     } else if (totalEatingTime < h) {  // eating speed too fast
        //        // slow down maximum eating speed.
        //        maxEatingSpeed = middleEatingSpeed - 1;
        //     } else {  // totalEatingTime > h, eating speed too slow
        //        // speed up minimum eating speed.
        //        minEatingSpeed = middleEatingSpeed + 1;
        //     }
        // }
        // return minEatingSpeed;

        // 1st try:
        // int minEatingSpeed = min(piles);
        // int maxEatingSpeed = max(piles);
        // while (minEatingSpeed <= maxEatingSpeed) {
        //     int middleEatingSpeed = (minEatingSpeed + maxEatingSpeed) / 2;
        //     int totalEatingTime = getTotalEatingTime(piles, middleEatingSpeed);
        //     if (totalEatingTime == h) {  // eating speed just right
        //         // return middleEatingSpeed;
        //         // slow down maximum eating speed.
        //         maxEatingSpeed = middleEatingSpeed - 1;
        //     } else if (totalEatingTime < h) {  // eating speed too fast
        //         // slow down maximum eating speed.
        //         maxEatingSpeed = middleEatingSpeed - 1;
        //     } else {  // totalEatingTime > h, eating speed too slow
        //         // speed up minimum eating speed.
        //         minEatingSpeed = middleEatingSpeed + 1;
        //     }
        // }
        // return maxEatingSpeed;  // should never reach this point?
    }

    private int getTotalEatingTime(int[] piles, int eatingSpeed) {
        // 3rd try:
        int totalEatingTime = 0;  // in hours
        for (int pileSize : piles) {
            totalEatingTime += Math.ceil((double) pileSize / eatingSpeed);
        }
        return totalEatingTime;

        // 1st try and 2nd try:
        // int totalEatingTime = 0;  // in hours
        // for (int pileSize : piles) {
        //     totalEatingTime += (int) Math.ceil((double) pileSize / eatingSpeed);
        // }
        // return totalEatingTime;
    }

    /**
     * Return the minimum element in the nums array.
     *
     * Preconditions:
     * - nums.length >= 1.
     */
    private int min(int[] nums) {
        // 2nd try:
        int minimum = Integer.MAX_VALUE;
        for (int num : nums) {
            minimum = Math.min(minimum, num);
        }
        return minimum;

        // 1st try:
        // int minimum = Integer.MAX_VALUE;
        // for (int num : nums) {
        //     if (num < minimum) {
        //         minimum = num;
        //     }
        // }
        // return minimum;
    }

    /**
     * Return the maximum element in the nums array.
     *
     * Preconditions:
     * - nums.length >= 1.
     */
    private int max(int[] nums) {
        // 2nd try and 3rd try:
        int maximum = 1;
        for (int num : nums) {
            maximum = Math.max(maximum, num);
        }
        return maximum;

        // 1st try:
        // int maximum = 1;
        // for (int num : nums) {
        //     if (num > maximum) {
        //         maximum = num;
        //     }
        // }
    }
}
