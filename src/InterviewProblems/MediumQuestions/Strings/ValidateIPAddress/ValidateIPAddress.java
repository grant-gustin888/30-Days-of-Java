package InterviewProblems.MediumQuestions.Strings.ValidateIPAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidateIPAddress {

    // Let n = the length of the queryIP string
    //
    // Time: O(n)
    // --> We must process all characters in the queryIP string in order to correctly determine
    // whether it is a valid IPv4 or IPv6 address.
    // Space: O(n)
    // --> We store all of the tokens of the queryIP in a temporary array.

    public static void main(String[] args) {
        ValidateIPAddress validateIPAddress = new ValidateIPAddress();

        // Input: queryIP = "172.16.254.1"
        // Output: "IPv4"
        // Explanation: This is a valid IPv4 address, return "IPv4".
        String validIPAddress1 = validateIPAddress.validIPAddress("172.16.254.1");
        System.out.println(validIPAddress1);  // IPv4

        // Input: queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
        // Output: "IPv6"
        // Explanation: This is a valid IPv6 address, return "IPv6".
        String validIPAddress2 = validateIPAddress.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334");
        System.out.println(validIPAddress2);  // IPv6

        // Input: queryIP = "256.256.256.256"
        // Output: "Neither"
        // Explanation: This is neither a IPv4 address nor a IPv6 address.
        String validIPAddress3 = validateIPAddress.validIPAddress("256.256.256.256");
        System.out.println(validIPAddress3);  // Neither

        // Input: queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334:"
        // Output: "Neither"
        // Explanation: This is neither a IPv4 address nor a IPv6 address.
        String validIPAddress4 = validateIPAddress.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:");
        System.out.println(validIPAddress4);  // Neither

        // Input: queryIP = "12..33.4"
        // Output: "Neither"
        // Explanation: This is neither a IPv4 address nor a IPv6 address.
        String validIPAddress5 = validateIPAddress.validIPAddress("12..33.4");
        System.out.println(validIPAddress5);  // Neither

        // Input: queryIP = "1.0.1."
        // Output: "Neither"
        // Explanation: This is neither a IPv4 address nor a IPv6 address.
        String validIPAddress6 = validateIPAddress.validIPAddress("1.0.1.");
        System.out.println(validIPAddress6);  // Neither

        // Input: "2001:0db8:85a3:0000:0000:8a2e:0370:7334"
        // Output: "IPv6"
        String validIPAddress7 = validateIPAddress.validIPAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334");
        System.out.println(validIPAddress7);  // IPv6

        // Input: "2001:db8:85a3:0:0:8A2E:0370:7334"
        // Output: "IPv6"
        String validIPAddress8 = validateIPAddress.validIPAddress("2001:db8:85a3:0:0:8A2E:0370:7334");
        System.out.println(validIPAddress8);  // IPv6

        // Input: "2001:0db8:85a3::8A2E:037j:7334"
        // Output: "Neither"
        String validIPAddress9 = validateIPAddress.validIPAddress("2001:0db8:85a3::8A2E:037j:7334");
        System.out.println(validIPAddress9);  // Neither

        // Input: "02001:0db8:85a3:0000:0000:8a2e:0370:7334"
        // Output: "Neither"
        String validIPAddress10 = validateIPAddress.validIPAddress("02001:0db8:85a3:0000:0000:8a2e:0370:7334");
        System.out.println(validIPAddress10);  // Neither

        // Input: "20EE:FGb8:85a3:0:0:8A2E:0370:7334"
        // Output: "Neither"
        // Explanation: This is neither a IPv4 address nor a IPv6 address.
        String validIPAddress11 = validateIPAddress.validIPAddress("20EE:FGb8:85a3:0:0:8A2E:0370:7334");
        System.out.println(validIPAddress11);  // Neither

        // Input: "11111111111111111111111111"
        // Output: "Neither"
        // Explanation: This is neither a IPv4 address nor a IPv6 address.
        String validIPAddress12 = validateIPAddress.validIPAddress("11111111111111111111111111");
        System.out.println(validIPAddress12);  // Neither
    }

    /**
     * Given a string queryIP, return "IPv4" if IP is a valid IPv4 address,
     * "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct
     * IP of any type.
     *
     * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255
     * and xi cannot contain leading zeros. For example, "192.168.1.1" and
     * "192.168.1.0" are valid IPv4 addresses while "192.168.01.1", "192.168.1.00",
     * and "192.168@1.1" are invalid IPv4 addresses.
     *
     * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
     * - 1 <= xi.length <= 4
     * - xi is a hexadecimal string which may contain digits, lowercase English letter
     * ('a' to 'f') and upper-case English letters ('A' to 'F').
     * - Leading zeros are allowed in xi.
     * - For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and
     * "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while
     * "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334"
     * are invalid IPv6 addresses.
     *
     * Preconditions:
     * - queryIP consists only of English letters, digits and the characters '.' and ':'.
     */
    public String validIPAddress(String queryIP) {
        if (isIPv4Address(queryIP)) {
            return "IPv4";
        } else if (isIPv6Address(queryIP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    private boolean isIPv4Address(String queryIP) {
        // tokenize queryIP by the delimiter "."
        List<String> IPv4Tokens = tokenize(queryIP, '.');
        // String[] IPv4Tokens = queryIP.split("\\.");  // todo: implement your own tokenize() function so "" is a valid token at the end of a string
        if (IPv4Tokens.size() != 4) {
            // IPv4 addresses must be of the form x1.x2.x3.x4
            return false;
        }

        for (String segment : IPv4Tokens) {
            if (Objects.equals(segment, "")) {
                // segment must not be empty
                return false;
            } else if (!correctLength(segment, 1, 3)) {
                // xi.length must be isBetween 1 - 3 (more precisely between 0 and 255)
                return false;
            } else if (containsLetters(segment)) {
                // segment must not contain uppercase or lowercase letters
                return false;
            } else if (containsLeadingZeroes(segment)) {  // !containsLetters(segment)
                // no leading zeroes allowed in IPv4 addresses
                return false;
            } else if (!isInRange(segment)) {  // !containsLeadingZeroes(segment)
                // xi must be isBetween 0 - 255
                return false;
            }
        }
        return true;
    }

    private boolean isIPv6Address(String queryIP) {
        // tokenize queryIP by the delimiter ":"
        List<String> IPv6Tokens = tokenize(queryIP, ':');
        // String[] IPv6Tokens = queryIP.split(":");  // todo: implement your own tokenize() function so "" is a valid token at the end of a string
        if (IPv6Tokens.size() != 8) {
            // IPv6 addresses must be of the form x1:x2:x3:x4:x5:x6:x7:x8
            return false;
        }

        for (String segment : IPv6Tokens) {
            if (!correctLength(segment, 1, 4)) {
                // xi.length must be isBetween 1 - 4
                return false;
            } else if (!isHexadecimalString(segment)) {
                // xi must be a hexadecimal string containing digits, lowercase letters, and uppercase letters.
                return false;
            }
        }
        return true;
    }

    private List<String> tokenize(String s, char c) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        for (char letter : s.toCharArray()) {
            if (letter == c) {
                tokens.add(token.toString());
                token = new StringBuilder();
            } else {
                token.append(letter);
            }
        }

        tokens.add(token.toString());
        token = new StringBuilder();

        return tokens;
    }

    private boolean containsLetters(String segment) {
        for (char c : segment.toCharArray()) {
            if (Character.isUpperCase(c) || Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsLeadingZeroes(String segment) {
        return segment.length() > 1 && segment.charAt(0) == '0';
    }

    private boolean isInRange(String segment) {
        int n = Integer.parseInt(segment);
        return 0 <= n && n <= 255;
    }

    private boolean correctLength(String segment, int minLength, int maxLength) {
        return minLength <= segment.length() && segment.length() <= maxLength;
    }

    private boolean isHexadecimalString(String segment) {
        for (char c : segment.toCharArray()) {
            if (!Character.isDigit(c) && !isBetween(c, 'A', 'F') && !isBetween(c, 'a', 'f')) {
                return false;
            }
        }
        return true;
    }

    private boolean isBetween(char letter, char startLetter, char endLetter) {
        return startLetter <= letter && letter <= endLetter;
    }
}
