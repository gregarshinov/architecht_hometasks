import java.util.ArrayList;
import java.util.stream.Collectors;

public class Expression {
    private final ArrayList<Token> infixTokens;
    private ArrayList<Token> reversePolishTokens;

    public Expression(String infixNotation) throws Exception {
        infixTokens = Tokenizer.tokenize(infixNotation);
        this.parse();
    }

    public String getReversePolishNotation() {
        return reversePolishTokens.stream().map(Token::toString).collect(Collectors.joining(" "));
    }

    public float solve() {
        Stack<Float> operands = new Stack<Float>();
        for (Token token : reversePolishTokens) {
            if (Tokenizer.isDigit(token.toString())) {
                operands.append(Float.valueOf(token.toString()));
            } else if (Tokenizer.isOperator(token.toString())) {
                Float op_2 = operands.pop();
                Float op_1 = operands.pop();
                switch (token.toString()) {
                    case "+":
                        operands.append(op_1 + op_2);
                        break;
                    case "-":
                        operands.append(op_1 - op_2);
                        break;
                    case "*":
                        operands.append(op_1 * op_2);
                        break;
                    case "/":
                        operands.append(op_1 / op_2);
                        break;
                }
            }
        }
        return operands.peep();
    }

    private void parse() throws Exception {
        Stack<Token> stack = new Stack<>();
        reversePolishTokens = new ArrayList<>();
        for (Token token : infixTokens) {
            if (Tokenizer.isDigit(token.toString())) {
                reversePolishTokens.add(token);
            } else if (Tokenizer.isOpeningParenthesis(token.toString())) {
                stack.append(token);
            } else if (Tokenizer.isOperator(token.toString())) {
                if (stack.size() != 0) {
                    Token stackTop = stack.peep();
                    if (stackTop != null) {
                        while (stackTop.getPrecedence() >= token.getPrecedence()
                                && !Tokenizer.isOpeningParenthesis(stackTop.toString())) {
                            reversePolishTokens.add(stack.pop());
                            stackTop = stack.peep();
                            if (stackTop == null) break;
                        }
                    }
                }
                stack.append(token);
            } else if (Tokenizer.isClosingParenthesis(token.toString())) {
                Token stackTop = stack.peep();
                while (!Tokenizer.isOpeningParenthesis(stackTop.toString())) {
                    reversePolishTokens.add(stack.pop());
                    stackTop = stack.peep();
                }
                stack.pop();
            }
        }
        Token stackTop = stack.peep();
        while (stack.size() != 0) {
            if (Tokenizer.isOpeningParenthesis(stackTop.toString()) || Tokenizer.isClosingParenthesis(stackTop.toString())) {
                throw new Exception("There is an error in the input expression");
            }
            reversePolishTokens.add(stack.pop());
            stackTop = stack.peep();
        }
    }

}
