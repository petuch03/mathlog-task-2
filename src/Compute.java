public class Compute {
    public static void computeCase() {
        FullExpression.getCurrentToRoot();
        if (computeTree(FullExpression.current)) {
            Values.trueRes++;
        } else {
            Values.falseRes++;
        }
    }

    public static boolean computeTree(Expression tree) {
        switch (tree.getOperation()) {
            case AND:
                return tree.getTrue() == (computeTree(tree.getLeftBranch()) && computeTree(tree.getRightBranch()));
            case OR:
                return tree.getTrue() == (computeTree(tree.getLeftBranch()) || computeTree(tree.getRightBranch()));
            case IMPL:
                return tree.getTrue() == ((!computeTree(tree.getLeftBranch())) || computeTree(tree.getRightBranch()));
            case SKOBKA:
                return tree.getTrue() == computeTree(tree.getLeftBranch());
            case VAR:
                return tree.getTrue() == Values.allBooleans.get(Values.allValues.get(tree.getVariable()));
            default:
                System.out.println("get Operation is not in [AND, OR, IMPL, SKOBKA, VAR]");
                System.exit(6);
        }
        throw new RuntimeException("aboba");
    }

    public static void setValues(){
        for (int i = 0; i < Math.pow(2, Values.allValues.size()); i++) {
            StringBuilder alreadyBinary = new StringBuilder(Integer.toBinaryString(i));
            int diff = Values.allValues.size() - alreadyBinary.length();
            for (int j = 0; j < diff; j++) {
                alreadyBinary.insert(0, "0");
            }
            for (int j = 0; j < Values.allValues.size(); j++) {
                if (alreadyBinary.charAt(j) == '0') {
                    Values.allBooleans.put(j, false);
                } else {
                    Values.allBooleans.put(j, true);
                }
            }
            computeCase();
        }
    }
}
