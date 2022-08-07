package InterviewProblems.MediumQuestions.Stacks.SimplifyPath;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class SimplifyPath {

    // Let n = the length of the file path string.
    //
    // Time: O(n)
    // --> O(n) to tokenize the file path string.
    // --> O(n) to build the simplified file path deque
    // --> O(n) to construct the simplified file path string
    // Space: O(n)
    // --> O(n) to store the tokens
    // --> O(n) to store the simplified file path deque

    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();

        // Input: path = "/../"
        // Output: "/"
        // Explanation: Going one level up from the root directory is a no-op, as the root
        // level is the highest level you can go.
        String path1 = "/../";
        String simplifiedFilePath1 = simplifyPath.simplifyPath(path1);
        System.out.println(simplifiedFilePath1);  // "/"

        // Input: path = "/home/"
        // Output: "/home"
        // Explanation: Note that there is no trailing slash after the last directory name.
        String path2 = "/home/";
        String simplifiedFilePath2 = simplifyPath.simplifyPath(path2);
        System.out.println(simplifiedFilePath2);  // "/home"

        // Input: path = "/home//foo/"
        // Output: "/home/foo"
        // Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
        String path3 = "/home//foo/";
        String simplifiedFilePath3 = simplifyPath.simplifyPath(path3);
        System.out.println(simplifiedFilePath3);  // "/home/foo"

        // Input: path = "/hello_diffusers"
        // Output: "/hello_diffusers"
        // Explanation: The input contains one or more slashes, which are all removed.
        String path4 = "/hello_diffusers";
        String simplifiedFilePath4 = simplifyPath.simplifyPath(path4);
        System.out.println(simplifiedFilePath4);  // "/hello_diffusers"

        // Input: path = "/hello diffusers"
        // Output: "/hello diffusers"
        // Explanation: The input contains one or more slashes, which are all removed.
        String path5 = "/hello diffusers";
        String simplifiedFilePath5 = simplifyPath.simplifyPath(path5);
        System.out.println(simplifiedFilePath5);  // "/hello diffusers"

        // Input: path = "/.."
        // Output: "/"
        // Explanation: Going one level up from the root directory is a no-op, as the root
        // level is the highest level you can go.
        String path6 = "/..";
        String simplifiedFilePath6 = simplifyPath.simplifyPath(path6);
        System.out.println(simplifiedFilePath6);  // "/"
    }

    /**
     * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory
     * in a Unix-style file system, convert it to the simplified canonical path.<br><br>
     *
     * In a Unix-style file system, a period '.' refers to the current directory, a double period '..'
     * refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated
     * as a single slash '/'. For this problem, any other format of periods such as '...' are treated
     * as file/directory names.<br><br>
     *
     * The canonical path should have the following format:<br>
     * - The path starts with a single slash '/'.<br>
     * - Any two directories are separated by a single slash '/'.<br>
     * - The path does not end with a trailing '/'.<br>
     * - The path only contains the directories on the path from the root directory to the target file <br>
     * or directory (i.e., no period '.' or double period '..')<br><br>
     *
     * Return the simplified canonical path.<br><br>
     *
     * Preconditions:<br>
     * - 1 <= path.length <= 3000.<br>
     * - path consists of English letters, digits, period '.', slash '/' or '_'.<br>
     * - path is a valid absolute Unix path.
     */
    public String simplifyPath(String path) {
        Deque<String> tokens = getTokens(path);
        Deque<String> simplifiedFilepath = getSimplifiedFilepath(tokens);
        return buildFilepath(simplifiedFilepath);
    }

    private Deque<String> getTokens(String path) {
        Deque<String> tokens = new ArrayDeque<>();
        StringBuilder token = new StringBuilder();
        for (char c : path.toCharArray()) {
            if (c != '/') {
                token.append(c);
            } else {  // c == '/'
                if (token.length() > 0) {
                    tokens.add(token.toString());
                    token = new StringBuilder();
                }

                if (Objects.equals(tokens.peekLast(), "/")) {
                    continue;
                }

                token.append(c);
                tokens.add(token.toString());
                token = new StringBuilder();
            }
        }

        // add remaining token to the list of tokens.
        if (token.length() > 0) {
            tokens.add(token.toString());
        }
        return tokens;
    }

    private static Deque<String> getSimplifiedFilepath(Deque<String> tokens) {
        Deque<String> simplifiedFilePath = new ArrayDeque<>();
        for (String token : tokens) {
            switch (token) {
                case "/":
                    // if it's a slash '/'
                    // if '/' is not the last element, add it to the deque
                    // otherwise ('/' exists at the end of the deque), don't add it to the deque
                    if (!Objects.equals(simplifiedFilePath.peekLast(), "/")) {
                        simplifiedFilePath.addLast(token);
                    } else {
                        continue;
                    }
                    break;
                case ".":
                    // if it's a period '.'
                    // skip it
                    continue;
                case "..":
                    // if it's a double period '..'
                    // ignore it if deque is empty,
                    // else pop off the cwd and '/'
                    if (simplifiedFilePath.size() <= 2) {
                        continue;
                    } else {
                        simplifiedFilePath.pollLast();  // pop cwd
                        simplifiedFilePath.pollLast();  // pop '/'
                    }
                    break;
                default:
                    // if it's a string (e.g., '...', 'abc', '123', etc.)
                    // add it to the file path
                    simplifiedFilePath.addLast(token);
                    break;
            }
        }

        // trim off the trailing '/' from the file path
        if (simplifiedFilePath.size() >= 2 && Objects.equals(simplifiedFilePath.peekLast(), "/")) {
            simplifiedFilePath.pollLast();
        }
        return simplifiedFilePath;
    }

    private String buildFilepath(Deque<String> simplifiedFilePath) {
        StringBuilder filePath = new StringBuilder();
        for (String token : simplifiedFilePath) {
            filePath.append(token);
        }
        return filePath.toString();
    }
}
