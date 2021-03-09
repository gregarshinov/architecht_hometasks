public class Calculator {
    public static void main(String[] args) throws Exception {
        Expression exp = new Expression(args[0]);
        System.out.println(exp.getReversePolishNotation());
        System.out.println(exp.solve());
    }
}
