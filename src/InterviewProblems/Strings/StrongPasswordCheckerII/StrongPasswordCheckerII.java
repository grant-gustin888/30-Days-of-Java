package InterviewProblems.Strings.StrongPasswordCheckerII;

public class StrongPasswordCheckerII {

    // Let n = the length of the password.
    //
    // Time: O(n)
    // --> We must traverse the password once every time we check each "strong password" criterion.
    // Space: O(1)
    // --> We don't need any additional data structures.

    public static void main(String[] args) {
        StrongPasswordCheckerII strongPasswordCheckerII = new StrongPasswordCheckerII();

        // Input: password = "IloveLe3tcode!"
        // Output: true
        // Explanation: The password meets all the requirements. Therefore, we return true.
        boolean isStrongPassword1 = strongPasswordCheckerII.strongPasswordCheckerII("IloveLe3tcode!");
        System.out.println(isStrongPassword1);  // true

        // Input: password = "Me+You--IsMyDream"
        // Output: false
        // Explanation: The password does not contain a digit and also contains 2 of the same character in adjacent positions. Therefore, we return false.
        boolean isStrongPassword2 = strongPasswordCheckerII.strongPasswordCheckerII("Me+You--IsMyDream");
        System.out.println(isStrongPassword2);  // false

        // Input: password = "1aB!"
        // Output: false
        // Explanation: The password does not meet the length requirement. Therefore, we return false.
        boolean isStrongPassword3 = strongPasswordCheckerII.strongPasswordCheckerII("1aB!");
        System.out.println(isStrongPassword3);  // false
    }

    /**
     * A password is said to be strong if it satisfies all the following criteria:
     *
     * - It has at least 8 characters.
     * - It contains at least one lowercase letter.
     * - It contains at least one uppercase letter.
     * - It contains at least one digit.
     * - It contains at least one special character. The special characters are the
     * characters in the following string: "!@#$%^&*()-+".
     * - It does not contain 2 of the same character in adjacent positions (i.e., "aab"
     * violates this condition, but "aba" does not).
     *
     * Given a string password, return true iff it is a strong password.
     *
     * Preconditions:
     * - 1 <= password.length <= 100.
     * - password consists of letters, digits, and special characters: "!@#$%^&*()-+".
     */

    public boolean strongPasswordCheckerII(String password) {
        return password.length() >= 8 && containsLowercaseLetter(password) && containsUppercaseLetter(password) &&
                containsDigit(password) && containsSpecialCharacters(password) && hasNoAdjacentRepeatingLetters(password);
    }

    private boolean containsLowercaseLetter(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsUppercaseLetter(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDigit(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsSpecialCharacters(String password) {
        String specialCharacters = "!@#$%^&*()-+";
        for (char c : password.toCharArray()) {
            if (specialCharacters.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    private boolean hasNoAdjacentRepeatingLetters(String password) {
        for (int i = 0; i < password.length(); i++) {
            char currentCharacter = password.charAt(i);
            char previousCharacter = (i == 0) ? '\0' : password.charAt(i - 1);
            if (previousCharacter == currentCharacter) {
                return false;
            } else {
                previousCharacter = currentCharacter;
            }
        }
        return true;
    }
}
