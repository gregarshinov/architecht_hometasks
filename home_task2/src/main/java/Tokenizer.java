import java.util.ArrayList;
import java.util.Arrays;

public class Tokenizer {
    static ArrayList<Token> tokenize(String infixExpression) {
        ArrayList<Token> tokens = new ArrayList<>();
        ArrayList<ArrayList<String>> tokenCandidates = new ArrayList<>();
        tokenCandidates.add(new ArrayList<>(Arrays.asList("")));
        ArrayList<String> lastToken;
        for (int charIdx = 0; charIdx < infixExpression.length(); charIdx++) {
            String character = String.valueOf(infixExpression.charAt(charIdx));
            if (isOpeningParenthesis(character)) {
                lastToken = tokenCandidates.get(tokenCandidates.size() - 1);
                lastToken.add(character);
                tokenCandidates.add(new ArrayList<>(Arrays.asList("")));
            } else if (isClosingParenthesis(character)) {
                tokenCandidates.add(new ArrayList<>(Arrays.asList("")));
                lastToken = tokenCandidates.get(tokenCandidates.size() - 1);
                lastToken.add(character);
            } else if (!isSpace(character)) {
                lastToken = tokenCandidates.get(tokenCandidates.size() - 1);
                lastToken.add(character);
            } else {
                tokenCandidates.add(new ArrayList<>(Arrays.asList("")));
            }
        }
        for (ArrayList<String> candidate : tokenCandidates) {
            String tokenString = String.join("", candidate);
            tokens.add(new Token(tokenString, getPrecedence(tokenString)));
        }
        return tokens;
    }

    static boolean isOpeningParenthesis(String seq) {
        return seq.equals("(");
    }

    static boolean isClosingParenthesis(String seq) {
        return seq.equals(")");
    }

    static boolean isSpace(String seq) {
        return seq.equals(" ");
    }

    static boolean isDigit(String seq) {
        return seq.matches("-?\\d+(\\.\\d+)?");
    }

    static boolean isOperator(String seq) {
        ArrayList<String> operators = new ArrayList<>(Arrays.asList("+", "-", "/", "*"));
        return operators.contains(seq);
    }

    private static int getPrecedence(String seq) {
        if (isOperator(seq) || isOpeningParenthesis(seq) || isClosingParenthesis(seq)) {
            int precedence = 0;
            switch (seq) {
                case "+":
                case "-":
                    precedence = 1;
                    break;
                case "*":
                case "/":
                    precedence = 2;
                    break;
                case "(":
                case ")":
                    precedence = 3;
                    break;
            }
            return precedence;
        }
        return -1;
    }
}
