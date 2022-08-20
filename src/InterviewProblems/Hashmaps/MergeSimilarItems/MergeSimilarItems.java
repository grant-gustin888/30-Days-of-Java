package InterviewProblems.Hashmaps.MergeSimilarItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MergeSimilarItems {

    // Let n1 = the length of the first array.
    // Let n2 = the length of the second array.
    //
    // Time: O((n1 + n2) * log(n1 + n2))
    // --> O(n1 + n2) for merging the two arrays.
    // --> O((n1 + n2) * log(n1 + n2)) for sorting the merged array by key.
    // Space: O(n1 + n2)
    // --> We must store the counts of all items if both arrays have no elements in common.

    public static void main(String[] args) {
        MergeSimilarItems mergeSimilarItems = new MergeSimilarItems();

        // Input: items1 = [[1, 1], [4, 5], [3, 8]], items2 = [[3, 1],[1, 5]]
        // Output: [[1, 6], [3, 9], [4, 5]]
        // Explanation:
        // The item with value = 1 occurs in items1 with weight = 1 and in items2 with weight = 5, total weight = 1 + 5 = 6.
        // The item with value = 3 occurs in items1 with weight = 8 and in items2 with weight = 1, total weight = 8 + 1 = 9.
        // The item with value = 4 occurs in items1 with weight = 5, total weight = 5.
        // Therefore, we return [[1, 6],[3, 9],[4, 5]].
        int[][] items1 = {{1, 1}, {4, 5}, {3, 8}};
        int[][] items2 = {{3, 1}, {1, 5}};
        List<List<Integer>> result = mergeSimilarItems.mergeSimilarItems(items1, items2);
        System.out.println(result);  // [[1, 6], [3, 9], [4, 5]]

        // Input: items1 = [[1, 1], [3, 2], [2, 3]], items2 = [[2, 1], [3, 2], [1, 3]]
        // Output: [[1, 4], [2, 4], [3, 4]]
        // Explanation:
        // The item with value = 1 occurs in items1 with weight = 1 and in items2 with weight = 3, total weight = 1 + 3 = 4.
        // The item with value = 2 occurs in items1 with weight = 3 and in items2 with weight = 1, total weight = 3 + 1 = 4.
        // The item with value = 3 occurs in items1 with weight = 2 and in items2 with weight = 2, total weight = 2 + 2 = 4.
        // Therefore, we return [[1, 4], [2, 4], [3, 4]].
        int[][] items3 = {{1, 1}, {3, 2}, {2, 3}};
        int[][] items4 = {{2, 1}, {3, 2}, {1, 3}};
        List<List<Integer>> result2 = mergeSimilarItems.mergeSimilarItems(items3, items4);
        System.out.println(result2);  // [[1, 4], [2, 4], [3, 4]]

        // Input: items1 = [[1, 3], [2, 2]], items2 = [[7, 1], [2, 2], [1, 4]]
        // Output: [[1, 7], [2, 4], [7, 1]]
        // Explanation:
        // The item with value = 1 occurs in items1 with weight = 3 and in items2 with weight = 4, total weight = 3 + 4 = 7.
        // The item with value = 2 occurs in items1 with weight = 2 and in items2 with weight = 2, total weight = 2 + 2 = 4.
        // The item with value = 7 occurs in items2 with weight = 1, total weight = 1.
        // Therefore, we return [[1, 7], [2, 4], [7, 1]].
        int[][] items5 = {{1, 3}, {2, 2}};
        int[][] items6 = {{7, 1}, {2, 2}, {1, 4}};
        List<List<Integer>> result3 = mergeSimilarItems.mergeSimilarItems(items5, items6);
        System.out.println(result3);  // [[1, 7], [2, 4], [7, 1]]
    }

    /**
     * You are given two 2D integer arrays, items1 and items2, representing two sets of items.<br>
     * Each array items has the following properties:<br>
     * - items[i] = [value_i, weight_i] where value_i represents the value and weight_i represents
     * the weight of the ith item.<br>
     * - The value of each item in items is unique.<br><br>
     *
     * Return a 2D integer array ret where ret[i] = [value_i, weight_i], with weight_i
     * being the sum of weights of all items with value value_i.<br><br>
     *
     * Note: ret should be returned in ascending order by value.<br><br>
     *
     * Preconditions:<br>
     * - 1 <= items1.length, items2.length <= 1000.<br>
     * - items1[i].length == items2[i].length == 2.<br>
     * - 1 <= value_i, weight_i <= 1000.<br>
     * - Each value_i in items1 is unique.<br>
     * - Each value_i in items2 is unique.
     */
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        // collect item counts in a hashmap
        // key: item value, value: item count
        // e.g. {1: 2, 2: 1, 3: 1}
        Map<Integer, Integer> itemCounts = getItemCounts(items1, items2);

        // sort items by key
        // e.g. [[1, 2], [2, 1], [3, 1]]
        return sortItems(itemCounts);
    }

    private Map<Integer, Integer> getItemCounts(int[][] items1, int[][] items2) {
        TreeMap<Integer, Integer> itemCounts = new TreeMap<>();
        for (int[] item : items1) {
            int key = item[0];
            int value = item[1];
            itemCounts.put(key, value);
        }

        for (int[] item : items2) {
            int key = item[0];
            int value = item[1];
            if (itemCounts.containsKey(key)) {
                int count = itemCounts.get(key);
                itemCounts.put(key, count + value);
            } else {
                itemCounts.put(key, value);
            }
        }

        return itemCounts;
    }

    private List<List<Integer>> sortItems(Map<Integer, Integer> itemCounts) {
        List<List<Integer>> sortedItems = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : itemCounts.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            sortedItems.add(Arrays.asList(key, value));
        }
        return sortedItems;
    }
}
