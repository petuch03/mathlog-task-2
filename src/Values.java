import java.util.HashMap;

public class Values {
    public static int sizeOfBooleans = 0;
    public static HashMap<String, Integer> allValues =  new HashMap<>();
    public static HashMap<Integer, Boolean> allBooleans =  new HashMap<>();
    public static int trueRes = 0;
    public static int falseRes = 0;

    public static void printResult(){
        if (trueRes == 0){
            System.out.println("Unsatisfiable");
        } else if (falseRes == 0){
            System.out.println("Valid");
        } else {
            System.out.println("Satisfiable and invalid, " + trueRes + " true and " + falseRes + " false cases");
        }
    }
}
