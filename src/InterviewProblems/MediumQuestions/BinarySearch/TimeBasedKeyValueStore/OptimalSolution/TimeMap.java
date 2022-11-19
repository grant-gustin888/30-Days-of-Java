package InterviewProblems.MediumQuestions.BinarySearch.TimeBasedKeyValueStore.OptimalSolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeMap {

    // Let n = the number of (value, timestamp) pairs in the timeMap data structure.
    //
    // constructor
    // Time: O(1)
    // Space: O(n)
    //
    // get()
    // Time: O(log n)
    // --> We use binary search to find the index of the node with the largest timestamp
    //     that is smaller than the target timestamp.
    // Space: O(1)
    // --> We only use a few variables to store the index of the start and end of the list
    //     and the index of the middle of the list.
    //
    // set()
    // Time: O(1)
    // --> We use a hashmap to store the key and the list of nodes.
    //     We use a list of nodes to store the value and the timestamp.
    // Space: O(1)
    // --> We only consume a fix amount of space per set() call.

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        // store the key "foo" and value "bar" along with timestamp = 1.
        System.out.println(timeMap.get("foo", 1));  // "bar"
        System.out.println(timeMap.get("foo", 3));  // "bar",
        // since there is no value corresponding to foo at timestamp 3 and timestamp 2,
        // then the only value is at timestamp 1 is "bar".
        timeMap.set("foo", "bar2", 4);
        // store the key "foo" and value "bar2" along with timestamp = 4.
        System.out.println(timeMap.get("foo", 4));  // "bar2"
        System.out.println(timeMap.get("foo", 5));  // "bar2"

        // ["TimeMap","set","set","get","get","get","get","get"]
        //[[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
        TimeMap timeMap2 = new TimeMap();
        timeMap2.set("love", "high", 10);
        timeMap2.set("love", "low", 20);
        System.out.println(timeMap2.get("love", 5));  // ""
        System.out.println(timeMap2.get("love", 10));  // "high"
        System.out.println(timeMap2.get("love", 15));  // "high"
        System.out.println(timeMap2.get("love", 20));  // "low"
        System.out.println(timeMap2.get("love", 25));  // "low"
    }

    private final Map<String, List<Node>> keysToNodes;

    /**
     * Design a time-based key-value data structure that can store multiple values for the
     * same key at different time stamps and retrieve the key's value at a certain timestamp.
     *
     * Implement the TimeMap class:
     *
     * TimeMap()
     * Initializes the object of the data structure.
     *
     * void set(String key, String value, int timestamp)
     * Stores the key key with the value value at the given time timestamp.
     *
     * String get(String key, int timestamp)
     * Returns a value such that set was called previously, with timestamp_prev <= timestamp.
     * If there are multiple such values, it returns the value associated with the largest
     * timestamp_prev. If there are no values, it returns "".
     *
     * Preconditions:
     * - 1 <= key.length, value.length <= 100
     * - key and value consist of lowercase English letters and digits.
     * - 1 <= timestamp <= 10 ^ 7.
     * - All the timestamps timestamp of set are strictly increasing.
     * - At most 2 * 105 calls will be made to set and get.
     */
    public TimeMap() {
        keysToNodes = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!keysToNodes.containsKey(key)) {
            keysToNodes.put(key, new ArrayList<>());
        }

        Node newNode = new Node(value, timestamp);
        keysToNodes.get(key).add(newNode);
    }

    /**
     * Return the value with the greatest timestamp such that
     * timestamp <= targetTimestamp.
     *
     * Preconditions:
     * - None.
     */
    public String get(String key, int timestamp) {
        if (!keysToNodes.containsKey(key)) {
            return "";
        }

        List<Node> nodes = keysToNodes.get(key);
        if (timestamp < nodes.get(0).timestamp) {
            return "";
        }

        int startIndex = 0;
        int endIndex = nodes.size() - 1;
        while (startIndex <= endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            Node currentNode = nodes.get(middleIndex);
            if (currentNode.timestamp == timestamp) {
                return currentNode.value;
            } else if (currentNode.timestamp < timestamp) {
                // find bigger timestamps by eliminate smaller timestamps
                startIndex = middleIndex + 1;
            } else {  // currentNode.timestamp > targetTimestamp
                // find smaller timestamps by eliminate larger timestamps
                endIndex = middleIndex - 1;
            }
        }

        // favour the largest timestamp closest to targetTimestamp.
        return nodes.get(endIndex).value;
    }
}
