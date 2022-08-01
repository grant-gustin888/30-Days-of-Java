package InterviewProblems.Hashmaps.FirstLetterToAppearTwice;

import java.util.HashSet;
import java.util.Set;

public class RepeatedCharacter {

    // Let n = the number of characters in the string s.
    //
    // Time: O(n)
    // --> We must potentially iterate through all characters in s to find
    // the duplicate character.
    // Space: O(n)
    // --> We must store at most n - 1 characters from s before we find the
    // duplicate character.

    public static void main(String[] args) {
        RepeatedCharacter repeated = new RepeatedCharacter();

        // Input: s = "aa"
        // Output: "a"
        // Explanation: The first character 'a' appears twice in the string,
        // so it must be the only repeating character.
        char repeatedCharacter1 = repeated.repeatedCharacter("aa");
        System.out.println(repeatedCharacter1);  // a

        // Input: s = "abcdd"
        // Output: "d"
        // Explanation:
        // The only letter that appears twice is 'd' so we return 'd'.
        char repeatedCharacter2 = repeated.repeatedCharacter("abcdd");
        System.out.println(repeatedCharacter2);  // d

        // Input: s = "abccbaacz"
        // Output: "c"
        // Explanation:
        // The letter 'a' appears on the indexes 0, 5 and 6.
        // The letter 'b' appears on the indexes 1 and 4.
        // The letter 'c' appears on the indexes 2, 3 and 7.
        // The letter 'z' appears on the index 8.
        // The letter 'c' is the first letter to appear twice,
        // because out of all the letters the index of its second occurrence
        // is the smallest.
        char repeatedCharacter3 = repeated.repeatedCharacter("abccbaacz");
        System.out.println(repeatedCharacter3);  // c
    }

    /**
     * Given a string s consisting of lowercase English letters,
     * return the first letter to appear twice.<br><br>
     *
     * Note:<br>
     * - A letter "a" appears twice before another letter b if the second occurrence
     * of "a" is before the second occurrence of "b".<br>
     * - s will contain at least one letter that appears twice.<br><br>
     *
     * Preconditions:<br>
     * - 2 <= s.length <= 100.<br>
     * - s consists of lowercase English letters.<br>
     * - s has at least one repeated letter.
     */
    public char repeatedCharacter(String s) {
        Set<Character> charactersSeen = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (charactersSeen.contains(c)) {
                return c;
            } else {
                charactersSeen.add(c);
            }
        }
        return '\0';  // This will never happen.
    }
}
