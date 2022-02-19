package as1_calc;

public class AddOpp extends Operator{

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        int V1 = op1.getValue();
        int V2 = op2.getValue();

        Operand res;
        res = new Operand((int) Math.pow(V1,V2));

        return res;
    }
}
