package Practical_2;

	import java.util.Stack;
	import java.util.Scanner;

	public class Testing_All {
	    public static void printHeader(String title) {
	        System.out.println("\n" + "=".repeat(50));
	        System.out.println(title);
	        System.out.println("=".repeat(50));
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            printHeader("STACK APPLICATIONS MENU");
	            System.out.println("1. Postfix Evaluation");
	            System.out.println("2. Balance Parentheses");
	            System.out.println("3. Run All Test Cases");
	            System.out.println("4. Exit");
	            System.out.print("\nEnter your choice: ");

	            // Input validation for integer choice
	            if (!scanner.hasNextInt()) {
	                System.out.println("\nInvalid input! Please enter a number.");
	                scanner.next(); // Consume the invalid token
	                continue;
	            }

	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume the newline character

	            switch (choice) {
	                case 1:
	                    handlePostfixEvaluation(scanner);
	                    break;

	                case 2:
	                    handleParenthesesCheck(scanner);
	                    break;

	                case 3:
	                    runAllTestCases();
	                    break;

	                case 4:
	                    System.out.println("\nExiting... Goodbye!");
	                    scanner.close();
	                    return;

	                default:
	                    System.out.println("\nInvalid choice! Please try again.");
	            }
	        }
	    }

	    private static void handlePostfixEvaluation(Scanner scanner) {
	        printHeader("POSTFIX EVALUATION");
	        System.out.print("Enter postfix expression (e.g., 234*+): ");
	        String postfix = scanner.nextLine().trim();

	        if (postfix.isEmpty()) {
	            System.out.println("\n✗ Error: Expression cannot be empty.");
	            return;
	        }

	        try {
	            int result = PostfixEvaluator.evaluate(postfix);
	            System.out.println("\n✓ Evaluation successful!");
	            System.out.println("Final Answer: " + result);
	        } catch (Exception e) {
	            System.out.println("\n✗ Error: " + e.getMessage());
	        }
	    }

	    private static void handleParenthesesCheck(Scanner scanner) {
	        printHeader("PARENTHESES BALANCE CHECK");
	        System.out.print("Enter expression (e.g., {[()]}): ");
	        String expression = scanner.nextLine().trim();

	        if (expression.isEmpty()) {
	            System.out.println("\nResult: BALANCED ✓ (Empty expression)");
	            return;
	        }

	        boolean balanced = ParenthesesBalancer.isBalanced(expression);

	        if (!balanced) {
	            int pos = ParenthesesBalancer.findFirstUnbalanced(expression);
	            if (pos != -1) {
	                System.out.println("\nFirst unbalanced element position (0-indexed): " + pos);
	            }
	        }
	    }

	    private static void runAllTestCases() {
	        printHeader("TEST CASES: POSTFIX EVALUATION");

	        String[] postfixTests = {
	            "23+",
	            "234*+",
	            "53+82-*",
	            "92/3+",
	            "62/32-*",
	        };

	        System.out.println("\nRunning " + postfixTests.length + " test cases...\n");

	        for (int i = 0; i < postfixTests.length; i++) {
	            System.out.println("Test " + (i + 1) + ": " + postfixTests[i]);
	            try {
	                PostfixEvaluator.evaluate(postfixTests[i]);
	            } catch (Exception e) {
	                System.out.println("Error: " + e.getMessage());
	            }
	            System.out.println();
	        }

	        printHeader("TEST CASES: PARENTHESES BALANCING");

	        String[] balanceTests = {
	            "(A+B)",
	            "{[()()]}",
	            "((A+B)",
	            "(A+B))",
	            "{(A+B]}",
	            ")(A+B",
	            " {[({[]})]}&  ",
	            "",
	        };

	        System.out.println("\nRunning " + balanceTests.length + " test cases...\n");

	        for (int i = 0; i < balanceTests.length; i++) {
	            System.out.println("Test " + (i + 1) + ": " + balanceTests[i]);
	            ParenthesesBalancer.isBalanced(balanceTests[i]);
	            System.out.println();
	        }

	        System.out.println("\n✓ All test cases completed!");
	    }
	}

	class PostfixEvaluator {

	    private static boolean isOperator(char ch) {
	        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
	    }

	    private static int applyOperator(int operand1, int operand2, char operator) {
	        switch (operator) {
	            case '+':
	                return operand1 + operand2;
	            case '-':
	                return operand1 - operand2;
	            case '*':
	                return operand1 * operand2;
	            case '/':
	                if (operand2 == 0) {
	                    throw new ArithmeticException("Division by zero!");
	                }
	                return operand1 / operand2;
	            case '^':
	                return (int) Math.pow(operand1, operand2);
	            default:
	                return 0; // Should not happen with valid input
	        }
	    }

	    public static int evaluate(String postfix) {
	        Stack<Integer> stack = new Stack<>();

	        System.out.println("\n=== Postfix Evaluation Steps ===");
	        System.out.println("Expression: " + postfix);
	        System.out.println("--------------------------------");

	        for (int i = 0; i < postfix.length(); i++) {
	            char ch = postfix.charAt(i);

	            if (ch == ' ') {
	                continue;
	            }

	            if (Character.isDigit(ch)) {
	                int digit = ch - '0';
	                stack.push(digit);
	                System.out.println("Step " + (i + 1) + ": Push " + digit + " → Stack: " + stack);
	            } else if (isOperator(ch)) {
	                if (stack.size() < 2) {
	                    throw new IllegalArgumentException("Invalid postfix expression! Not enough operands for operator '" + ch + "' at position " + i);
	                }

	                int operand2 = stack.pop();
	                int operand1 = stack.pop();
	                int result = applyOperator(operand1, operand2, ch);

	                stack.push(result);
	                System.out.println("Step " + (i + 1) + ": " + operand1 + " " + ch + " " + operand2 + " = " + result + " → Stack: " + stack);
	            } else {
	                throw new IllegalArgumentException("Invalid character '" + ch + "' found in expression.");
	            }
	        }

	        if (stack.size() != 1) {
	            throw new IllegalArgumentException("Invalid postfix expression! Stack has " + stack.size() + " elements remaining.");
	        }

	        int finalResult = stack.pop();
	        System.out.println("\nIntermediate Result: " + finalResult);
	        return finalResult;
	    }
	}

	class ParenthesesBalancer {

	    private static boolean isOpeningBracket(char ch) {
	        return ch == '(' || ch == '{' || ch == '[';
	    }

	    private static boolean isClosingBracket(char ch) {
	        return ch == ')' || ch == '}' || ch == ']';
	    }

	    private static boolean isMatchingPair(char opening, char closing) {
	        return (opening == '(' && closing == ')') ||
	               (opening == '{' && closing == '}') ||
	               (opening == '[' && closing == ']');
	    }

	    public static boolean isBalanced(String expression) {
	        Stack<Character> stack = new Stack<>();

	        System.out.println("\n=== Parentheses Balance Check ===");
	        System.out.println("Expression: " + expression);
	        System.out.println("----------------------------------");

	        for (int i = 0; i < expression.length(); i++) {
	            char ch = expression.charAt(i);

	            if (isOpeningBracket(ch)) {
	                stack.push(ch);
	                System.out.println("Position " + i + ": '" + ch + "' (Opening) → Push → Stack: " + stack);
	            } else if (isClosingBracket(ch)) {
	                if (stack.isEmpty()) {
	                    System.out.println("Position " + i + ": '" + ch + "' (Closing) → Stack EMPTY!");
	                    System.out.println("\nResult: UNBALANCED ✗");
	                    System.out.println("Reason: No matching opening bracket");
	                    return false;
	                }

	                char top = stack.pop();
	                if (!isMatchingPair(top, ch)) {
	                    System.out.println("Position " + i + ": '" + ch + "' → Popped '" + top + "' → MISMATCH!");
	                    System.out.println("\nResult: UNBALANCED ✗");
	                    System.out.println("Reason: Expected closing for '" + top + "' but found '" + ch + "'");
	                    return false;
	                }

	                System.out.println("Position " + i + ": '" + ch + "' (Closing) → Popped '" + top + "' → MATCH! → Stack: " + (stack.isEmpty() ? "[]" : stack.toString()));
	            } else {
	                System.out.println("Position " + i + ": '" + ch + "' (Character) → Ignore");
	            }
	        }

	        if (stack.isEmpty()) {
	            System.out.println("\nStack is EMPTY → All brackets matched!");
	            System.out.println("Result: BALANCED ✓");
	            return true;
	        } else {
	            System.out.println("\nStack NOT EMPTY → Remaining: " + stack);
	            System.out.println("Result: UNBALANCED ✗");
	            System.out.println("Reason: " + stack.size() + " opening bracket(s) not closed");
	            return false;
	        }
	    }

	    public static int findFirstUnbalanced(String expression) {
	        Stack<Integer> stack = new Stack<>();

	        for (int i = 0; i < expression.length(); i++) {
	            char ch = expression.charAt(i);

	            if (isOpeningBracket(ch)) {
	                stack.push(i);
	            } else if (isClosingBracket(ch)) {
	                if (stack.isEmpty()) {
	                    return i; // First unbalanced closing bracket
	                }
	                stack.pop();
	            }
	        }

	        // If the loop finishes and the stack is not empty, the first unclosed opening bracket is the one we want.
	        // The problem description in the prompt implied finding the *first* issue in the string order.
	        // The current logic for isBalanced already handles finding the first issue sequentially.
	        // This helper method is slightly redundant given the `isBalanced` output but follows the original structure.
	        return stack.isEmpty() ? -1 : stack.peek();
	    }
	}
