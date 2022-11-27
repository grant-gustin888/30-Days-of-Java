package InterviewProblems.MediumQuestions.PriorityQueue.TaskScheduler.CorrectSolution;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler {

    // inspired by NeetCode video: https://neetcode.io/practice
    // Heap / Priority Queue > Task Scheduler

    // Let n = the number of tasks in the tasks array.
    // Let m = the idle time required between two identical tasks.
    //
    // Time: O(n * m)
    // --> In the worst case, the tasks array could be filled with all the same tasks,
    // so we'd have to incur the idle time for each task.
    // Space: O(n)
    // --> O(n) for the task frequency hashmap.
    // --> O(n) for the max heap of task frequencies.
    // --> O(n) for the queue of cooloff task objects.

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
        Map<Character, Integer> taskCounts = getTaskCounts(tasks);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);  // max heap of task frequencies
        for (int taskCount : taskCounts.values()) {
            maxHeap.add(taskCount);
        }

        int currentTime = 0;
        Deque<CoolOffTask> coolOffTasks = new LinkedList<>();

        while (!maxHeap.isEmpty() || !coolOffTasks.isEmpty()) {
            currentTime++;
            if (!maxHeap.isEmpty()) {
                int newCount = maxHeap.poll() - 1;
                if (newCount >= 1) {
                    CoolOffTask newCoolOffTask = new CoolOffTask(newCount, currentTime + n);
                    coolOffTasks.add(newCoolOffTask);
                }
            }

            if (!coolOffTasks.isEmpty() && coolOffTasks.peek().endTime == currentTime) {
                CoolOffTask oldCoolOffTask = coolOffTasks.poll();
                maxHeap.add(oldCoolOffTask.taskCount);
            }
        }

        return currentTime;
    }

    private Map<Character, Integer> getTaskCounts(char[] tasks) {
        Map<Character, Integer> taskCounts = new HashMap<>();
        for (char task : tasks) {
            taskCounts.put(task, taskCounts.getOrDefault(task, 0) + 1);
        }
        return taskCounts;
    }
}
