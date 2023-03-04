package InterviewProblems.MediumQuestions.Backtracking.PalindromePartitioning;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    // Time: O(2 ^ n)
    // --> We must consider all possible partitions of the string.
    //
    // Space: O(h) = O(n)
    // --> The number of recursive calls on the call stack is proportional to the
    //

    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();

        // Input: s = "a"
        // Output: [["a"]]
        List<List<String>> partitions1 = palindromePartitioning.partition("a");
        System.out.println(partitions1);  // [[a]]
        palindromePartitioning.currentPartition = new ArrayList<>();
        palindromePartitioning.allPartitions = new ArrayList<>();

        // Input: s = "aab"
        // Output: [["a", "a", "b"], ["aa", "b"]]
        List<List<String>> partitions2 = palindromePartitioning.partition("aab");
        System.out.println(partitions2);  // [[a, a, b], [aa, b]]
        palindromePartitioning.currentPartition = new ArrayList<>();
        palindromePartitioning.allPartitions = new ArrayList<>();
    }

    List<List<String>> allPartitions = new ArrayList<>();
    List<String> currentPartition = new ArrayList<>();

    /**
     * Given a string s, partition s such that every substring of the partition
     * is a palindrome.
     *
     * Return all possible palindrome partitioning of s.
     *
     * Preconditions:
     * - 1 <= s.length <= 16.
     * - s contains only lowercase English letters.
     */
    public List<List<String>> partition(String s) {
        getPartitions(s, 0);
        return allPartitions;
    }

    private void getPartitions(String s, int index) {
        if (index >= s.length()) {
            List<String> newPartition = new ArrayList<>(currentPartition);
            allPartitions.add(newPartition);
            return;
        }

        for (int j = index; j < s.length(); j++) {
            if (isPalindrome(s, index, j)) {
                currentPartition.add(s.substring(index, j + 1));
                getPartitions(s, j + 1);
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
