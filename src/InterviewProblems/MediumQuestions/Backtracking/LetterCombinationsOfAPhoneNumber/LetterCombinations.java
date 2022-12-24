package InterviewProblems.MediumQuestions.Backtracking.LetterCombinationsOfAPhoneNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    // Let n = the length of the digits string.
    //
    // Time: O(4 ^ n)
    // --> A single digit can have up to 4 letters to branch off of.
    // Space: O(n)
    // --> The call stack will only contain up to O(n) recursive calls, one for
    // each digit in the digits string.

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();

        // Input: digits = ""
        // Output: []
        List<String> letterCombinations1 = letterCombinations.letterCombinations("");
        System.out.println(letterCombinations1);  // []
        letterCombinations.allLetterCombinations = new ArrayList<>();
        letterCombinations.currentLetterCombination = "";

        // Input: digits = "2"
        // Output: ["a", "b", "c"]
        List<String> letterCombinations2 = letterCombinations.letterCombinations("2");
        System.out.println(letterCombinations2);  // [a, b, c]
        letterCombinations.allLetterCombinations = new ArrayList<>();
        letterCombinations.currentLetterCombination = "";

        // Input: digits = "23"
        // Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
        List<String> letterCombinations3 = letterCombinations.letterCombinations("23");
        System.out.println(letterCombinations3);  // [ad, ae, af, bd, be, bf, cd, ce, cf]
        letterCombinations.allLetterCombinations = new ArrayList<>();
        letterCombinations.currentLetterCombination = "";
    }

    List<String> allLetterCombinations = new ArrayList<>();
    String currentLetterCombination = "";
    Map<Character, String[]> digitsToLetters;

    public List<String> letterCombinations(String digits) {
        digitsToLetters = getDigitToLetters();
        getLetterCombinations(digits, 0);
        return allLetterCombinations;
    }

    private void getLetterCombinations(String digits, int index) {
        if (index >= digits.length()) {
            if (currentLetterCombination != "") {
                allLetterCombinations.add(currentLetterCombination);
            }
            return;  // no more combinations
        }

        char currentDigit = digits.charAt(index);
        String[] letters = digitsToLetters.get(currentDigit);
        for (String letter : letters) {
            currentLetterCombination += letter;
            getLetterCombinations(digits, index + 1);
            // remove last char
            currentLetterCombination = currentLetterCombination.substring(0, currentLetterCombination.length() - 1);
        }
    }

    private Map<Character, String[]> getDigitToLetters() {
        Map<Character, String[]> digitsToLetters = new HashMap<>();
        digitsToLetters.put('2', new String[] {"a", "b", "c"});
        digitsToLetters.put('3', new String[] {"d", "e", "f"});
        digitsToLetters.put('4', new String[] {"g", "h", "i"});
        digitsToLetters.put('5', new String[] {"j", "k", "l"});
        digitsToLetters.put('6', new String[] {"m", "n", "o"});
        digitsToLetters.put('7', new String[] {"p", "q", "r", "s"});
        digitsToLetters.put('8', new String[] {"t", "u", "v"});
        digitsToLetters.put('9', new String[] {"w", "x", "y", "z"});
        return digitsToLetters;
    }
}
