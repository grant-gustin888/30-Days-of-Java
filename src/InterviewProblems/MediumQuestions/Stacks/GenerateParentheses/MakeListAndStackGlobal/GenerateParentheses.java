package InterviewProblems.MediumQuestions.Stacks.GenerateParentheses.MakeListAndStackGlobal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenerateParentheses {

    List<String> validParenthesesCombinations = new ArrayList<>();
    Stack<String> characterStack = new Stack<>();

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
        getAllParentheses(0, 0, n);
        return validParenthesesCombinations;
    }

    private void getAllParentheses(int numOpenParentheses, int numClosedParentheses, int n) {
        // append valid parentheses sequence to the list of parentheses combinations
        if (numOpenParentheses == n && numClosedParentheses == n) {
            String stackAsParenthesesString = getParenthesesString(characterStack);
            validParenthesesCombinations.add(stackAsParenthesesString);
            return;
        }

        // append a "("
        if (numOpenParentheses < n) {
            characterStack.push("(");
            getAllParentheses(numOpenParentheses + 1, numClosedParentheses, n);
            characterStack.pop();
        }

        // append a ")"
        if (numClosedParentheses < numOpenParentheses) {
            characterStack.push(")");
            getAllParentheses(numOpenParentheses, numClosedParentheses + 1, n);
            characterStack.pop();
        }
    }

    private String getParenthesesString(Stack<String> characterStack) {
        StringBuilder parenthesesString = new StringBuilder();
        for (String parenthesis : characterStack) {
            parenthesesString.append(parenthesis);
        }
        return parenthesesString.toString();
    }
}
