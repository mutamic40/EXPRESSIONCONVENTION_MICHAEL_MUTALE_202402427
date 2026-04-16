import java.util.*;

public class ExpressionConverter {

    public static List<String> tokenize(String infix) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < infix.length()) {
            char c = infix.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }
            if (Character.isDigit(c)) {
                // Build multi-digit number
                StringBuilder num = new StringBuilder();
                while (i < infix.length() && Character.isDigit(infix.charAt(i))) {
                    num.append(infix.charAt(i));
                    i++;
                }
                tokens.add(num.toString());
                continue; // i already advanced
            }
            if (Character.isLetter(c)) {
                // Single-letter variable
                tokens.add(String.valueOf(c));
                i++;
                continue;
            }
            // Parentheses or operators
            if (c == '(' || c == ')' || isOperator(c)) {
                tokens.add(String.valueOf(c));
                i++;
                continue;
            }
            // Ignore any other character (you can throw an exception for production code)
            i++;
        }
        return tokens;
    }

    private static boolean isOperator(char c) {
        return "+-*/^".indexOf(c) != -1;
    }

    private static int precedence(String op) {
        char c = op.charAt(0);
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    private static boolean isLeftAssociative(String op) {
        return op.charAt(0) != '^'; // ^ is right-associative
    }

    private static boolean isOperand(String token) {
        if (token.isEmpty()) return false;
        char first = token.charAt(0);
        return Character.isLetterOrDigit(first);
    }

    /**
     * Converts tokenized infix to postfix using Shunting-yard algorithm.
     */
    public static List<String> infixToPostfix(List<String> tokens) {
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperand(token)) {
                result.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    result.add(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // remove '('
                }
            } else if (token.length() == 1 && isOperator(token.charAt(0))) {
                // Operator handling with precedence and associativity
                while (!stack.isEmpty() &&
                       !stack.peek().equals("(") &&
                       (precedence(stack.peek()) > precedence(token) ||
                        (precedence(stack.peek()) == precedence(token) && isLeftAssociative(token)))) {
                    result.add(stack.pop());
                }
                stack.push(token);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    /**
     * Converts infix expression to postfix (space-separated string).
     */
    public static String toPostfix(String infix) {
        List<String> tokens = tokenize(infix);
        List<String> postfixList = infixToPostfix(tokens);
        return String.join(" ", postfixList);
    }

    /**
     * Converts infix expression to prefix (space-separated string).
     * Uses the standard reverse-and-swap method internally.
     */
    public static String toPrefix(String infix) {
        List<String> tokens = tokenize(infix);

        // Step 1: Reverse the tokens
        List<String> revTokens = new ArrayList<>(tokens);
        Collections.reverse(revTokens);

        // Step 2: Swap parentheses
        for (int i = 0; i < revTokens.size(); i++) {
            String t = revTokens.get(i);
            if (t.equals("(")) {
                revTokens.set(i, ")");
            } else if (t.equals(")")) {
                revTokens.set(i, "(");
            }
        }

        // Step 3: Convert reversed (swapped) expression to postfix
        List<String> postfixOfRev = infixToPostfix(revTokens);

        // Step 4: Reverse the result to get prefix
        Collections.reverse(postfixOfRev);
        return String.join(" ", postfixOfRev);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter infix expression (e.g., (10 + 2) * 3^2 or A + B * C): ");
            String infix = scanner.nextLine();
            
            String postfix = toPostfix(infix);
            String prefix = toPrefix(infix);
            
            System.out.println("Infix   : " + infix);
            System.out.println("Postfix : " + postfix);
            System.out.println("Prefix  : " + prefix);
        }
    }
}