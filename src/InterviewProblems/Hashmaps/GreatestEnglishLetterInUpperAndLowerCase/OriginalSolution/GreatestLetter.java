package InterviewProblems.Hashmaps.GreatestEnglishLetterInUpperAndLowerCase.OriginalSolution;

public class GreatestLetter {

    // Let n = the length of the string s.
    //
    // Time: O(n)
    // --> O(1) to build both arrays
    // --> O(n) to record all n characters in the string s
    // --> O(1) to check the existence of each character in the string s
    // for both an uppercase and lowercase English letter
    // Space: O(1)
    // --> O(1) to create an array of 26 booleans to record all lowercase
    // and uppercase letters seen.

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
        boolean[] lowerCaseCharactersSeen = new boolean[26];
        boolean[] upperCaseCharactersSeen = new boolean[26];
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lowerCaseCharactersSeen[c - 'a'] = true;
            } else {  // Character.isUpperCase(c)
                upperCaseCharactersSeen[c - 'A'] = true;
            }
        }

        // We want the greatest English letter which occurs as both a lowercase and
        // uppercase letter in s.
        for (char c = 'Z'; c >= 'A'; c--) {
            if (lowerCaseCharactersSeen[c - 'A'] && upperCaseCharactersSeen[c - 'A']) {
                return Character.toString(c);
            }
        }
        return "";
    }
}
