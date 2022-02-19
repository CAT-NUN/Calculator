package as1_calc;

public class CloseParOpp extends Operator{

    @Override
    public int priority() {
        return 5;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}
