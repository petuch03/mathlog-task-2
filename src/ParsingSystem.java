import java.util.LinkedList;
import java.util.List;

public class ParsingSystem {
    private static int position = 0;
    private static List<String> data = new LinkedList<>();
    // global var to ! sign
    public static boolean localIsTrue = true;

    public static void defaultParser() {
        try {
            String nowValue = ParsingSystem.data.get(ParsingSystem.position++);

            if (nowValue.equals("&") || nowValue.equals("|") || nowValue.equals("->")) {
                FullExpression.addOperNode(ParsingSystem.getTreeFromOper(nowValue));
            } else if (nowValue.equals("!")) {
                ParsingSystem.localIsTrue = !ParsingSystem.localIsTrue;
            } else {
                if (nowValue.equals("(") || nowValue.equals(")")) {
                    FullExpression.addSkobka(nowValue);
                } else {
                    FullExpression.addVarNode(ParsingSystem.getTreeFromVar(nowValue));
                }
            }

            defaultParser();
        } catch (RuntimeException ignored) {
        }
    }

    public static Expression getTreeFromVar(String var) {
        if (localIsTrue) {
            return new Expression(null, null, null, var, Expression.Operation.VAR, true);
        } else {
            Expression result = new Expression(null, null, null, var, Expression.Operation.VAR, false);
            localIsTrue = true;
            return result;
        }
    }

    public static Expression getTreeFromOper(String oper) {
        Expression.Operation operation = null;
        switch (oper) {
            case "&":
                operation = Expression.Operation.AND;
                break;
            case "->":
                operation = Expression.Operation.IMPL;
                break;
            case "|":
                operation = Expression.Operation.OR;
                break;
        }
        return new Expression(null, null, null, oper, operation, true);
    }

    void run(List<String> data) {
        ParsingSystem.data = data;
        position = 0;
        defaultParser();
    }
}