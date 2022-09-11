package InterviewProblems.MediumQuestions.Stacks.CarFleet.OriginalSolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CarFleet {

    public static void main(String[] args) {
        CarFleet carFleet = new CarFleet();

        // Input: target = 10, position = [3], speed = [3]
        // Output: 1
        // Explanation: There is only one car, hence there is only one fleet.
        int[] position1 = {3};
        int[] speed1 = {3};
        int carFleet1 = carFleet.carFleet(10, position1, speed1);
        System.out.println(carFleet1);  // 1

        // Input: target = 10, position = [2, 4], speed = [3, 2]
        // Output: 1
        // Explanation: The cars starting at 4 and 2 become a fleet, meeting each other at 6.
        // The car starting at 3 doesn't catch up to any other car, so it is a fleet by itself.
        int[] position2 = {2, 4};
        int[] speed2 = {3, 2};
        int carFleet2 = carFleet.carFleet(10, position2, speed2);
        System.out.println(carFleet2);  // 1

        // Input: target = 12, position = [10, 8, 0, 5, 3], speed = [2, 4, 1, 1, 3]
        // Output: 3
        // Explanation:
        // The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12.
        // The car starting at 0 does not catch up to any other car, so it is a fleet by itself.
        // The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6.
        // The fleet moves at speed 1 until it reaches target.
        // Note that no other cars meet these fleets before the destination, so the answer is 3.
        int[] position3 = {10, 8, 0, 5, 3};
        int[] speed3 = {2, 4, 1, 1, 3};
        int carFleet3 = carFleet.carFleet(12, position3, speed3);
        System.out.println(carFleet3);  // 3

        // Input: target = 100, position = [0, 2, 4], speed = [4, 2, 1]
        // Output: 1
        // Explanation:
        // The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4.
        // The fleet moves at speed 2.
        // Then, the fleet (speed 2) and the car starting at 4 (speed 1) become one fleet,
        // meeting each other at 6. The fleet moves at speed 1 until it reaches target.
        // Note that no other cars meet these fleets before the destination, so the answer is 1.
        int[] position4 = {0, 2, 4};
        int[] speed4 = {4, 2, 1};
        int carFleet4 = carFleet.carFleet(100, position4, speed4);
        System.out.println(carFleet4);  // 1
    }

    /**
     * There are n cars going to the same destination along a one-lane road.
     * The destination is target miles away.<br><br>
     *
     * You are given two integer array position and speed, both of length n, where position[i]
     * is the position of the ith car and speed[i] is the speed of the ith car (in miles per hour).<br><br>
     *
     * A car can never pass another car ahead of it, but it can catch up to it and drive bumper
     * to bumper at the same speed. The faster car will slow down to match the slower car's speed.
     * The distance between these two cars is ignored (i.e., they are assumed to have the same position).<br><br>
     *
     * A car fleet is some non-empty set of cars driving at the same position and same speed.
     * Note that a single car is also a car fleet.<br><br>
     *
     * If a car catches up to a car fleet right at the destination point, it will still be
     * considered as one car fleet.<br><br>
     *
     * Return the number of car fleets that will arrive at the destination.<br><br>
     *
     * Preconditions:<br>
     * - n == position.length == speed.length.<br>
     * - 1 <= n <= 10 ^ 5.<br>
     * - 0 < target <= 10 ^ 6.<br>
     * - 0 <= position[i] < target.<br>
     * - All the values of position are unique.<br>
     * - 0 < speed[i] <= 10 ^ 6.
     */
    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length == 1) {
            return 1;
        }

        List<List<Integer>> positionSpeedPairs = getPositionSpeedPairs(position, speed);
        sortCarPositionsInDescendingOrder(positionSpeedPairs);

        Stack<Double> carTimesToDestinations = new Stack<>();
        for (List<Integer> positionSpeedPair : positionSpeedPairs) {
            int currentPosition = positionSpeedPair.get(0);
            int currentSpeed = positionSpeedPair.get(1);
            double currentCarTimeToDestination = (double) (target - currentPosition) / currentSpeed;
            double previousCarTimeToDestination = !carTimesToDestinations.isEmpty() ? carTimesToDestinations.peek() : -1;

            if (carTimesToDestinations.isEmpty()) {
                carTimesToDestinations.push(currentCarTimeToDestination);
            } else if (previousCarTimeToDestination < currentCarTimeToDestination) {
                carTimesToDestinations.push(currentCarTimeToDestination);
            }
        }
        return carTimesToDestinations.size();
    }

    private List<List<Integer>> getPositionSpeedPairs(int[] position, int[] speed) {
        List<List<Integer>> positionSpeedPairs = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            List<Integer> positionSpeedPair = new ArrayList<>();
            positionSpeedPair.add(position[i]);
            positionSpeedPair.add(speed[i]);
            positionSpeedPairs.add(positionSpeedPair);
        }
        return positionSpeedPairs;
    }

    private void sortCarPositionsInDescendingOrder(List<List<Integer>> positionSpeedPairs) {
        positionSpeedPairs.sort((positionSpeedPair1, positionSpeedPair2) ->
                positionSpeedPair2.get(0).compareTo(positionSpeedPair1.get(0)));
    }
}
