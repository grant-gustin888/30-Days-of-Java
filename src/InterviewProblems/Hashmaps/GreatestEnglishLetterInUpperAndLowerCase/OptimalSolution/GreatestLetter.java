package InterviewProblems.Hashmaps.GreatestEnglishLetterInUpperAndLowerCase.OptimalSolution;

public class GreatestLetter {

    // Sample 1ms leetcode solution:
    // https://leetcode.com/submissions/detail/823337058/

    // Let n = the length of string s.
    //
    // Time: O(n)
    // --> We iterate through at most 26 (lowercase and uppercase) characters,
    // regardless of the length of string s
    // --> the loop body is O(n) because we need to search through each character
    // of string s to see if it contains both the lowercase and uppercase
    // versions of the current character.
    // Space: O(1)
    // --> We don't need any additional data structures.

    public static void main(String[] args) {
        GreatestLetter greatestLetter = new GreatestLetter();

        // Input: s = "AbCdEfGhIjK"
        // Output: ""
        // Explanation:
        // There is no letter that appears in both lower and upper case.
        String greatestLetter1 = greatestLetter.greatestLetter("AbCdEfGhIjK");
        System.out.println(greatestLetter1);  // ""

        // Input: s = "lEeTcOdE"
        // Output: "E"
        // Explanation:
        // The letter 'E' is the only letter to appear in both lower and upper case.
        String greatestLetter2 = greatestLetter.greatestLetter("lEeTcOdE");
        System.out.println(greatestLetter2);  // "E"

        // Input: s = "arRAzFif"
        // Output: "R"
        // Explanation:
        // The letter 'R' is the greatest letter to appear in both lower and upper case.
        // Note that 'A' and 'F' also appear in both lower and upper case, but 'R' is greater than 'F' or 'A'.
        String greatestLetter3 = greatestLetter.greatestLetter("arRAzFif");
        System.out.println(greatestLetter3);  // "R"
    }

    /**
     * Given a string of English letters s, return the greatest English letter
     * which occurs as both a lowercase and uppercase letter in s. The returned
     * letter should be in uppercase. If no such letter exists, return an empty
     * string.
     *
     * An English letter b is greater than another letter a if b appears after
     * a in the English alphabet.
     *
     * Preconditions:
     * - 1 <= s.length <= 1000.
     * - s consists of lowercase and uppercase English letters.
     */
    public String greatestLetter(String s) {
        for (char upperCaseLetter = 'Z', lowerCaseLetter = 'z';
             upperCaseLetter >= 'A' && lowerCaseLetter >= 'a';
             upperCaseLetter--, lowerCaseLetter--) {
            if (s.indexOf(upperCaseLetter) != -1 && s.indexOf(lowerCaseLetter) != -1) {
                return Character.toString(upperCaseLetter);
            }
        }
        return "";
    }
}
