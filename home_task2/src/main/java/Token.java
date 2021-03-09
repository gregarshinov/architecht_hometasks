public class Token{

    private final String characters;
    private final int precedence;

    Token(String chars, int precedence) {
        characters = chars;
        this.precedence = precedence;
    }

    @Override
    public String toString() {
        return characters;
    }

    public int getPrecedence() {
        return precedence;
    }
}
