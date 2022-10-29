package InterviewProblems.MediumQuestions.Strings.EncodeAndDecodeStrings.StringCompression;

public class StringCompression {

    // Let n = the length of the chars array
    //
    // Time: O(n)
    // --> We must traverse through the chars array once.
    // Space: O(1)
    // --> We modify the chars array in place, and we only store 5 variables: currentCharacter,
    // numConsecutiveCharacters, compressedStringLength, and letter.

    public static void main(String[] args) {
        StringCompression stringCompression = new StringCompression();

        // Input: chars = ["a"]
        // Output: Return 1, and the first character of the input array should be: ["a"]
        // Explanation: The only group is "a", which remains uncompressed since it's a single character.
        char[] chars1 = {'a'};
        int compressedStringLength1 = stringCompression.compress(chars1);
        System.out.println(compressedStringLength1);  // 1

        // Input: chars = ["a","a","b","b","c","c","c"]
        // Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
        // Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
        char[] chars2 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int compressedStringLength2 = stringCompression.compress(chars2);
        System.out.println(compressedStringLength2);  // 6

        // Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
        // Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
        // Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
        char[] chars3 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        int compressedStringLength3 = stringCompression.compress(chars3);
        System.out.println(compressedStringLength3);  // 4
    }

    /**
     * Given an array of characters chars, compress it using the following algorithm:
     *
     * Begin with an empty string s. For each group of consecutive repeating characters in chars:
     *
     * If the group's length is 1, append the character to s.
     * Otherwise, append the character followed by the group's length.
     * The compressed string s should not be returned separately, but instead, be stored in the input
     * character array chars. Note that group lengths that are 10 or longer will be split into multiple
     * characters in chars.
     *
     * After you are done modifying the input array, return the new length of the array.
     *
     * You must write an algorithm that uses only constant extra space.
     *
     * Preconditions:
     * - 1 <= chars.length <= 2000.
     * - chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
     */
    public int compress(char[] chars) {
        char currentCharacter = '\0';
        int numConsecutiveCharacters = 0;

        int compressedStringLength = 0;
        for (char letter : chars) {
            if (currentCharacter == '\0') {
                // reset currentCharacter and numConsecutiveCharacters
                currentCharacter = letter;
                numConsecutiveCharacters = 1;
            } else if (letter == currentCharacter) {  // currentCharacter != '\0'
                numConsecutiveCharacters++;
            } else {  // letter != currentCharacter
                // record string length in chars array
                compressedStringLength = recordCompressedString(chars, currentCharacter, numConsecutiveCharacters, compressedStringLength);

                // reset currentCharacter and numConsecutiveCharacters
                currentCharacter = letter;
                numConsecutiveCharacters = 1;
            }
        }

        // record string length in chars array at the end as well...
        compressedStringLength = recordCompressedString(chars, currentCharacter, numConsecutiveCharacters, compressedStringLength);

        return compressedStringLength;
    }

    private int recordCompressedString(char[] chars, char currentCharacter, int numConsecutiveCharacters, int compressedStringLength) {
        // record letter
        chars[compressedStringLength] = currentCharacter;
        compressedStringLength++;

        // record integer by parsing integer digit by digit....
        if (numConsecutiveCharacters >= 2) {
            String digits = Integer.toString(numConsecutiveCharacters);
            for (char digit : digits.toCharArray()) {
                chars[compressedStringLength] = digit;
                compressedStringLength++;
            }
        }
        return compressedStringLength;
    }
}
