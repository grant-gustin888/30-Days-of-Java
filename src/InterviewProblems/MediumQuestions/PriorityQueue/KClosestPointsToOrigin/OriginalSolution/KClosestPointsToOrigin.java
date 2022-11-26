package InterviewProblems.MediumQuestions.PriorityQueue.KClosestPointsToOrigin.OriginalSolution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    // Let n = the number of points in the points array.
    // Let k = the top k closest points to the origin to return, where k <= n.
    //
    // Time: O(n log n)
    // --> O(n) to add all points to the hashmap
    // --> O(n log n) to add all points to the minimum priority queue
    // --> O(k log n) to extract the top k points from the minimum priority queue
    // Space: O(n)
    // --> O(n) for the hashmap.
    // --> O(n) for the minimum priority queue.

    public static void main(String[] args) {
        KClosestPointsToOrigin kClosestPointsToOrigin = new KClosestPointsToOrigin();

        // Input: points = [[1, 3], [-2, 2]], k = 1
        // Output: [[-2, 2]]
        // Explanation:
        // The distance between (1, 3) and the origin is sqrt(10).
        // The distance between (-2, 2) and the origin is sqrt(8).
        // Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
        // We only want the closest k = 1 points from the origin, so the answer is just [[-2, 2]].
        int[][] points1 = {{1, 3}, {-2, 2}};
        int[][] kClosestPoints1 = kClosestPointsToOrigin.kClosest(points1, 1);
        System.out.println(Arrays.deepToString(kClosestPoints1));  // [[-2, 2]]

        // Input: points = [[3, 3], [5, -1], [-2, 4]], k = 2
        // Output: [[3, 3], [-2, 4]]
        // Explanation: The answer [[-2, 4], [3, 3]] would also be accepted.
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int[][] kClosestPoints2 = kClosestPointsToOrigin.kClosest(points2, 2);
        System.out.println(Arrays.deepToString(kClosestPoints2));  // [[3, 3], [-2, 4]]

        // Input: points = [[2, 2], [2, 2]], k = 2
        // Output: [[2, 2], [2, 2]]
        int[][] points3 = {{2, 2}, {2, 2}};
        int[][] kClosestPoints3 = kClosestPointsToOrigin.kClosest(points3, 2);
        System.out.println(Arrays.deepToString(kClosestPoints3));  // [[2, 2], [2, 2]]

        // TODO: implement using binary search or using the quickselect algorithm.
    }

    /**
     * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane
     * and an integer k, return the k closest points to the origin (0, 0).
     *
     * The distance between two points on the X-Y plane is the Euclidean distance (i.e.,
     * √(x1 - x2) ^ 2 + (y1 - y2) ^ 2).
     *
     * You may return the answer in any order. The answer is guaranteed to be unique
     * (except for the order that it is in).
     *
     * Preconditions:
     * - 1 <= k <= points.length <= 10 ^ 4.
     * - -10 ^ 4 < xi, yi < 10 ^ 4.
     */
    public int[][] kClosest(int[][] points, int k) {
        // Can I assume that all of the points are unique?
        // No. There can be duplicate points.
        Map<int[], Double> pointsToOriginDistances = getPointsToDistances(points);

        // place all points in a priority queue
        PriorityQueue<int[]> closestPointsToOrigin = new PriorityQueue<>((point1, point2) -> {
            double distance1 = pointsToOriginDistances.get(point1);
            double distance2 = pointsToOriginDistances.get(point2);

            // smallest distance first
            if (distance1 < distance2) {
                return -1;
            } else if (distance1 > distance2) {
                return 1;
            } else {  // distance1 == distance2
                return 0;
            }
        });
        for (int[] point : points) {
            closestPointsToOrigin.add(point);
        }

        // extract top k points from priority queue
        int[][] topKClosestPoints = new int[k][2];
        for (int i = 0; i < k; i++) {
            topKClosestPoints[i] = closestPointsToOrigin.poll();
        }
        return topKClosestPoints;
    }

    private Map<int[], Double> getPointsToDistances(int[][] points) {
        Map<int[], Double> pointsToDistances = new HashMap<>();
        for (int[] point : points) {
            double distance = getDistance(point);
            pointsToDistances.put(point, distance);
        }
        return pointsToDistances;
    }

    private double getDistance(int[] point) {
        return Math.sqrt(point[0] * point[0] + point[1] * point[1]);
    }
}
