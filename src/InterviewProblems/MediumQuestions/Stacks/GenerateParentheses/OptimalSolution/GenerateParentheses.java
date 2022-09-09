package InterviewProblems.MediumQuestions.Stacks.GenerateParentheses.OptimalSolution;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();

        // Input: n = 1
        // Output: ["()"]
        List<String> parentheses1 = generateParentheses.generateParenthesis(1);
        System.out.println(parentheses1);  // ["()"]

        // Input: n = 2
        // Output: ["(())", "()()"]
        List<String> parentheses2 = generateParentheses.generateParenthesis(2);
        System.out.println(parentheses2);  // ["(())", "()()"]

        // Input: n = 3
        // Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
        List<String> parentheses3 = generateParentheses.generateParenthesis(3);
        System.out.println(parentheses3);  // ["((()))", "(()())", "(())()", "()(())", "()()()"]
    }

    /**
     * Given n pairs of parentheses, ... generate all combinations of
     * well-formed parentheses.<br><br>
     *
     * Preconditions:<br>
     * - 1 <= n <= 8.
     */
    public List<String> generateParenthesis(int n) {
        List<String> validParenthesesCombinations = new ArrayList<>();
        StringBuilder currentParenthesesCombination = new StringBuilder();
        getAllParentheses(0, 0, n, currentParenthesesCombination, validParenthesesCombinations);
        return validParenthesesCombinations;
    }

    private void getAllParentheses(int numOpenParentheses, int numClosedParentheses, int n,
                                   StringBuilder currentParenthesesCombination, List<String> validParenthesesCombinations) {
        // append valid parentheses sequence to the list of parentheses combinations
        if (numOpenParentheses == n && numClosedParentheses == n) {
            String parenthesesCombination = currentParenthesesCombination.toString();
            validParenthesesCombinations.add(parenthesesCombination);
            return;
        }

        // append a "("
        if (numOpenParentheses < n) {
            int originalLength = currentParenthesesCombination.length();
            currentParenthesesCombination.append("(");
            getAllParentheses(numOpenParentheses + 1, numClosedParentheses, n, currentParenthesesCombination, validParenthesesCombinations);
            currentParenthesesCombination.setLength(originalLength);
        }

        // append a ")"
        if (numClosedParentheses < numOpenParentheses) {
            int originalLength = currentParenthesesCombination.length();
            currentParenthesesCombination.append(")");
            getAllParentheses(numOpenParentheses, numClosedParentheses + 1, n, currentParenthesesCombination, validParenthesesCombinations);
            currentParenthesesCombination.setLength(originalLength);
        }
    }
}
