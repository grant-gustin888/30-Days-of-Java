package InterviewProblems.MediumQuestions.CurrentlyDoingTheseQuestions.EncodeAndDecodeStrings.EncodeAndDecodeStrings;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {

    // Let n = the number of characters across all words
    //
    // Time: O(n)
    // --> We must traverse the entire string to encode and decode it.
    // Space: O(n)
    // --> We must encode the list of string using all characters in the string,
    // the length of each word, and the # symbol.

    public static void main(String[] args) {
        EncodeAndDecodeStrings encodeDecode = new EncodeAndDecodeStrings();

        // Input: ["lint", "code", "love", "you"]
        // Output: ["lint", "code", "love", "you"]
        // Explanation: One possible encode method is: "lint:;code:;love:;you"
        List<String> originalStrings1 = new ArrayList<>();
        originalStrings1.add("lint");
        originalStrings1.add("code");
        originalStrings1.add("love");
        originalStrings1.add("you");
        String encodedString = encodeDecode.encode(originalStrings1);
        System.out.println(encodedString);  // 4#lint4#code4#love3#you
        List<String> decodedStrings = encodeDecode.decode(encodedString);
        System.out.println(decodedStrings);  // [lint, code, love, you]

        // Input: ["we", "say", ":", "yes"]
        // Output: ["we", "say", ":", "yes"]
        // Explanation:
        // One possible encode method is: "we:;say:;:::;yes"
        List<String> originalStrings2 = new ArrayList<>();
        originalStrings2.add("we");
        originalStrings2.add("say");
        originalStrings2.add(":");
        originalStrings2.add("yes");
        String encodedString2 = encodeDecode.encode(originalStrings2);
        System.out.println(encodedString2);  // 2#we3#say1#:3#yes
        List<String> decodedStrings2 = encodeDecode.decode(encodedString2);
        System.out.println(decodedStrings2);  // [we, say, :, yes]
    }

    /**
     * Take a list of decoded strings and encode them into a single string.<br><br>
     *
     * Preconditions:<br>
     * - all characters are english letters.
     */
    public String encode(List<String> decodedStrings) {
        // String decodedString = "";
        // for (String s : decodedStrings) {
        //     if (!s.contains(":")) {
        //         decodedString += s + ":;";
        //     } else {  // s.contains(":")
        //        decodedString += s + "::;";
        //    }
        // }
        // trim last :;
        // decodedString = decodedString.substring(0, decodedString.length() - 2);
        // return decodedString;
        String decodedString = "";
        for (String s : decodedStrings) {
            decodedString += s.length() + "#" + s;
        }
        return decodedString;
    }

    /**
     * Take an encoded string and decode it into a list of decoded strings.
     */
    public List<String> decode(String encodedString) {
        // List<String> decodedStrings = new ArrayList<>();
        // String newWord = "";
        // for (int i = 0; i < encodedString.length(); i++) {
        //    char currentCharacter = encodedString.charAt(i);
        //    char nextCharacter = (i + 1 < encodedString.length()) ? encodedString.charAt(i + 1) : '\0';
        //    if (currentCharacter != ':' && currentCharacter != ';') {
        //        newWord += currentCharacter;
        //    } else if (currentCharacter == ':') {
        //        if (nextCharacter == ';') {
        //            decodedStrings.add(newWord);
        //            newWord = "";
        //        } else {
        //            newWord += currentCharacter;
        //        }
        //    } else {  // currentCharacter == ';'
        //        newWord += currentCharacter;
        //    }
        // }
        // return decodedStrings;
        List<String> decodedStrings = new ArrayList<>();
        int currentIndex = 0;
        while (currentIndex < encodedString.length()) {
            int indexOfFirstCharacter = getIndexOfFirstCharacter(encodedString, currentIndex);
            int lengthOfString = extractStringLength(encodedString, currentIndex, indexOfFirstCharacter);
            String newWord = encodedString.substring(indexOfFirstCharacter + 1, indexOfFirstCharacter + 1 + lengthOfString);
            decodedStrings.add(newWord);
            currentIndex = indexOfFirstCharacter + 1 + lengthOfString;
        }
        return decodedStrings;
    }

    private int getIndexOfFirstCharacter(String encodedString, int startIndex) {
        int indexOfFirstCharacter = startIndex;
        while (encodedString.charAt(indexOfFirstCharacter) != '#') {
            indexOfFirstCharacter++;
        }
        return indexOfFirstCharacter;
    }

    private int extractStringLength(String encodedString, int currentIndex, int indexOfFirstCharacter) {
        return Integer.parseInt(encodedString.substring(currentIndex, indexOfFirstCharacter));
    }
}
