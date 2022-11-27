package InterviewProblems.MediumQuestions.PriorityQueue.TaskScheduler.BuggySolution;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler {

    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler();

        // Input: tasks = ["A", "A", "A", "B", "B", "B"], n = 2
        // Output: 8
        // Explanation:
        // A -> B -> idle -> A -> B -> idle -> A -> B
        // There is at least 2 units of time between any two same tasks.
        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int totalTime1 = taskScheduler.leastInterval(tasks1, 2);
        System.out.println(totalTime1);  // 8

        // Input: tasks = ["A", "A", "A", "B", "B", "B"], n = 0
        // Output: 6
        // Explanation: On this case any permutation of size 6 would work since n = 0.
        // ["A", "A", "A", "B", "B", "B"]
        // ["A", "B", "A", "B", "A", "B"]
        // ["B", "B", "B", "A", "A", "A"]
        // ...
        // And so on.
        char[] tasks2 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int totalTime2 = taskScheduler.leastInterval(tasks2, 0);
        System.out.println(totalTime2);  // 6

        // Input: tasks = ["A", "A", "A", "A", "A", "A", "B", "C", "D", "E", "F", "G"], n = 2
        // Output: 16
        // Explanation:
        // One possible solution is
        // A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
        char[] tasks3 = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int totalTime3 = taskScheduler.leastInterval(tasks3, 2);
        System.out.println(totalTime3);  // 16
    }

    /**
     * Given a characters array tasks, representing the tasks a CPU needs to do,
     * where each letter represents a different task. Tasks could be done in any order.
     * Each task is done in one unit of time. For each unit of time, the CPU could complete
     * either one task or just be idle.
     *
     * However, there is a non-negative integer n that represents the cooldown period
     * between two same tasks (the same letter in the array), that is that there must be
     * at least n units of time between any two same tasks.
     *
     * Return the least number of units of times that the CPU will take to finish all
     * the given tasks.
     *
     * Preconditions:
     * - 1 <= task.length <= 10 ^ 4.
     * - tasks[i] is upper-case English letter.
     * - The integer n is in the range [0, 100].
     */
    public int leastInterval(char[] tasks, int n) {
        // count up task frequencies
        Map<Character, Integer> taskFrequencies = getTaskFrequencies(tasks);

        // create a priority queue sorted by most frequent task first
        // create a map of all tasks in cool-off mode
        // start the timer.
        int timeElapsed = 0;
        Map<Character, Integer> coolOffTasks = new HashMap<>();
        PriorityQueue<Character> remainingTasks = new PriorityQueue<>((task1, task2) -> {
            int taskFrequency1 = taskFrequencies.get(task1);
            int taskFrequency2 = taskFrequencies.get(task2);
            return taskFrequency2 - taskFrequency1;  // most frequent task first.
        });
        for (Map.Entry<Character, Integer> entry : taskFrequencies.entrySet()) {
            char task = entry.getKey();
            remainingTasks.add(task);
        }

        // go through tasks one by one, perform most frequent task,
        // consume one unit of time for all tasks in coolOff,
        // then add recently done task to coolOff.
        while (!remainingTasks.isEmpty() || !coolOffTasks.isEmpty()) {

            // we can either:
            // - perform a task (if any)
            // - remain idle (if remaining tasks is empty, but all tasks are in cooloff)
            if (!remainingTasks.isEmpty()) {
                // perform most frequent task
                char activeTask = remainingTasks.poll();
                int activeTaskFrequency = taskFrequencies.get(activeTask);
                if (activeTaskFrequency >= 1) {  // if it's <= 0, leave it alone...
                    taskFrequencies.put(activeTask, activeTaskFrequency - 1);
                } else {
                    // if it's <= 0, leave it alone...
                    taskFrequencies.remove(activeTask);
                }

                // update all cooloff times
                updateAllCoolOffTimes(coolOffTasks, remainingTasks);

                // add incomplete task to cooloff, if cooloff time is required.
                if (activeTaskFrequency >= 1 && n >= 1) {
                    coolOffTasks.put(activeTask, n);
                }
            } else {  // remainingTasks.isEmpty() !coolOffTasks.isEmpty() -- so remain idle
                // update all cooloff times
                updateAllCoolOffTimes(coolOffTasks, remainingTasks);
            }

            timeElapsed++;
        }
        return timeElapsed;
    }

    private Map<Character, Integer> getTaskFrequencies(char[] tasks) {
        Map<Character, Integer> taskFrequencies = new HashMap<>();
        for (char task : tasks) {
            taskFrequencies.put(task, taskFrequencies.getOrDefault(task, 0) + 1);
        }
        return taskFrequencies;
    }

    private void updateAllCoolOffTimes(Map<Character, Integer> coolOffTasks, PriorityQueue<Character> remainingTasks) {
        for (Map.Entry<Character, Integer> entry : coolOffTasks.entrySet()) {
            char task = entry.getKey();
            int coolOffTimeLeft = entry.getValue();

            // TODO: update cool off for this task here.
            // TODO: ignore tasks with cool off time <= 0, we may need to add them back to to cool off tasks again
            if (coolOffTimeLeft >= 1) {
                coolOffTasks.put(task, coolOffTimeLeft - 1);
                if (coolOffTimeLeft - 1 <= 0) {
                    remainingTasks.add(task);
                }
            }
        }
    }
}
