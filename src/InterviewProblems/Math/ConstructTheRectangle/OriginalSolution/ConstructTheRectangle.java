package InterviewProblems.Math.ConstructTheRectangle.OriginalSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstructTheRectangle {

    // Let n = the value of area.
    //
    // Time: O(n)
    // --> We go through the numbers from sqrt(n) to n, which is n - sqrt(n) + 1 numbers.
    // Space: O(n)
    // --> We create an array with up to n pairs of dimensions.

    public static void main(String[] args) {
        ConstructTheRectangle constructTheRectangle = new ConstructTheRectangle();

        // Input: area = 4
        // Output: [2, 2]
        // Explanation: The target area is 4, and all the possible ways to
        // construct it are [1, 4], [2, 2], [4, 1].
        // But according to requirement 2, [1, 4] is illegal; according to
        // requirement 3, [4, 1] is not optimal compared to [2, 2].
        // So the length L is 2, and the width W is 2.
        int[] dimensions1 = constructTheRectangle.constructRectangle(4);
        System.out.println(Arrays.toString(dimensions1));  // [2, 2]

        // Input: area = 37
        // Output: [37, 1]
        // Explanation: The target area is 37, and all the possible ways to
        // construct it are [1, 37], [37, 1].
        // But according to requirement 2, [1, 37] is illegal; according to
        // requirement 3, [37, 1] is optimal compared to [1, 37].
        // So the length L is 37, and the width W is 1.
        int[] dimensions2 = constructTheRectangle.constructRectangle(37);
        System.out.println(Arrays.toString(dimensions2));  // [37, 1]

        // Input: area = 122122
        // Output: [427, 286]
        int[] dimensions3 = constructTheRectangle.constructRectangle(122122);
        System.out.println(Arrays.toString(dimensions3));  // [427, 286]

        // Input: area = 10000000
        // Output: [3200, 3125]
        int[] dimensions4 = constructTheRectangle.constructRectangle(10000000);
        System.out.println(Arrays.toString(dimensions4));  // [3200, 3125]
    }

    /**
     * A web developer needs to know how to design a web page's size.
     * So, given a specific rectangular web page’s area, your job by now is to
     * design a rectangular web page, whose length L and width W satisfy the
     * following requirements:<br><br>
     *
     * 1. The area of the rectangular web page you designed must equal to the
     * given target area.<br>
     * 2. The width W should not be larger than the length L, which means L >= W.<br>
     * 3. The difference between length L and width W should be as small as possible.<br><br>
     *
     * Return an array [L, W] where L and W are the length and width of the
     * web page you designed in sequence.<br>
     *
     * Preconditions:<br>
     * - 1 <= area <= 10 ^ 7.
     */
    public int[] constructRectangle(int area) {
        List<List<Integer>> possibleDimensions = getPossibleDimensions(area);
        return findClosestDimensions(possibleDimensions);
    }

    private List<List<Integer>> getPossibleDimensions(int area) {
        List<List<Integer>> possibleDimensions = new ArrayList<>();
        int maxArea = (int) Math.sqrt(area);
        for (int length = maxArea; length <= area; length++) {
            if (area % length == 0 && length >= area / length) {
                List<Integer> dimensions = new ArrayList<>();
                dimensions.add(length);
                dimensions.add(area / length);
                possibleDimensions.add(dimensions);
            }
        }
        return possibleDimensions;
    }

    private int[] findClosestDimensions(List<List<Integer>> possibleDimensions) {
        int[] closestDimensions = new int[2];
        int minDifference = Integer.MAX_VALUE;
        for (List<Integer> dimensions : possibleDimensions) {
            int difference = Math.abs(dimensions.get(0) - dimensions.get(1));
            if (difference < minDifference) {
                minDifference = difference;
                closestDimensions[0] = dimensions.get(0);
                closestDimensions[1] = dimensions.get(1);
            }
        }
        return closestDimensions;
    }
}
