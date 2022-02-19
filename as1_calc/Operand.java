package as1_calc;

public class Operand {
    private String operand;
    private int value;

    public Operand( String operand ) {
        if(check(operand)){
            this.value = Integer.parseInt(operand);
        }
    }

    public Operand( int value ) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static boolean check( String token ) {
        try {
            Integer.parseInt(token);
        }
        catch (NumberFormatException e) {
            System.out.println("Not a number");
            return false;
        }
        return true;
    }
}