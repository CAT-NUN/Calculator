package as1_calc;
import java.util.HashMap;
public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.

    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );

    /*
            public class RpgPersonExample
        {
           public static Map<String, RpgPerson> team = new HashMap<>();
           public static void main( String[] args )
           {
           }
        }
     */

    private static HashMap<String, Operator> HashOp;
    static{
        HashOp = new HashMap<>();
        HashOp.put("+", new AddOpp());
        HashOp.put("-", new MinusOpp());
        HashOp.put("*", new MultOpp());
        HashOp.put("/", new DivOpp());
        HashOp.put("^", new PowerOpp());
        HashOp.put("(", new OpenParOpp());
        HashOp.put(")", new CloseParOpp());
    }

    public abstract int priority();

    public abstract Operand execute(Operand op1, Operand op2 );


    public static boolean check( String token ) {
        if(HashOp.containsKey(token)){
            return true;
        }
        else{
            return false;
        }
    }

    public static Operator getOpp(String token){
        if(check(token) == true){
            return HashOp.get(token);
        }
        else{
            return null;
        }
    }


}

