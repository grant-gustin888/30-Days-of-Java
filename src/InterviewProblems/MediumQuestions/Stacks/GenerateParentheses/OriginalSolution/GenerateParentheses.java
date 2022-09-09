package InterviewProblems.MediumQuestions.Stacks.GenerateParentheses.OriginalSolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        Stack<String> characterStack = new Stack<>();
        getAllParentheses(0, 0, n, validParenthesesCombinations, characterStack);
        return validParenthesesCombinations;
    }

    private void getAllParentheses(int numOpenParentheses, int numClosedParentheses, int n,
                                   List<String> validParenthesesCombinations, Stack<String> characterStack) {
        // append valid parentheses sequence to the list of parentheses combinations
        if (numOpenParentheses == n && numClosedParentheses == n) {
            String stackAsParenthesesString = getParenthesesString(characterStack);
            validParenthesesCombinations.add(stackAsParenthesesString);
            return;
        }

        // append a "("
        if (numOpenParentheses < n) {
            characterStack.push("(");
            getAllParentheses(numOpenParentheses + 1, numClosedParentheses, n, validParenthesesCombinations, characterStack);
            characterStack.pop();
        }

        // append a ")"
        if (numClosedParentheses < numOpenParentheses) {
            characterStack.push(")");
            getAllParentheses(numOpenParentheses, numClosedParentheses + 1, n, validParenthesesCombinations, characterStack);
            characterStack.pop();
        }
    }

    private String getParenthesesString(Stack<String> characterStack) {
        String parenthesesString = "";
        for (String parenthesis : characterStack) {
            parenthesesString += parenthesis;
        }
        return parenthesesString;
    }
}
