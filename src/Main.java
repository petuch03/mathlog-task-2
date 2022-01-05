import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) { // ((A->B)->C)->D
        Scanner scanner = new Scanner(System.in);
        String test = scanner.next();
        new ParsingSystem().run(preparser(test));
        //String result = FullExpression.toStringAllTree();
        //System.out.println(result);
        Compute.setValues();
        Values.printResult();
    }

    public static List<String> preparser(String input) {
        return zipMetavariables(parserSpaces(input));
    }

    public static List<String> parserSpaces(String input) { //((PPP->PPP')->PPP)->PPP
//        input = input.replace("\r", "");
//        input = input.replace("\n", "");
//        input = input.replace(" ", "");
//        input = input.replace(">", "");
        String[] inp = input.split("");

//        return Arrays.asList(inp);
        List<String> data = Arrays.asList(inp);
        return data.stream()
                .filter(i -> !Objects.equals(i, " ") && !Objects.equals(i, ">") && !Objects.equals(i, "\r") && !Objects.equals(i, "\n"))
                .collect(Collectors.toList());
    }

    public static List<String> zipMetavariables(List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            if (isVariable(data.get(i)) && i != data.size() - 1 && isVariable(data.get(i + 1))) {
                data.set(i, data.get(i) + data.get(i + 1));
                data.remove(i + 1);
                i--;
                continue;
            }
            if (data.get(i).equals("-")) {
                data.set(i, "->");
            }
        }
        return data;
    }

    public static Boolean isVariable(String theString) {
        return theString.matches("[A-Z]?([0-9A-Z']*)");
    }
}