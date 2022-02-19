package as1_calc;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;

    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "+-*^/() ";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval( String expression ) {
        operandStack.clear();
        operatorStack.clear();

        int res = 0;

        String token;

        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

        while ( this.tokenizer.hasMoreTokens() ) {

            // filter out spaces
            if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {
                // check if token is an operand
                if ( Operand.check( token )) {
                    operandStack.push( new Operand( token ));
                } else {
                    if ( ! Operator.check( token )) {
                        System.out.println( "*****invalid token******" );
                        System.exit( 1 );
                    }

                    // TODO Operator is abstract - this line will need to be fixed:
                    // ( The Operator class should contain an instance of a HashMap,
                    // and values will be instances of the Operators.  See Operator class
                    // skeleton for an example. )
                    Operator newOperator = Operator.getOpp( token );//

                    while ( operatorStack.peek().priority() >= newOperator.priority()  && !operatorStack.isEmpty()) {
                        // note that when we eval the expression 1 - 2 we will
                        // push the 1 then the 2 and then do the subtraction operation
                        // This means that the first number to be popped is the
                        // second operand, not the first operand - see the following code

                        if(operatorStack.peek().equals("(")){
                            int sum;
                            int Ind1st = expression.indexOf("(");
                            int Ind2nd = expression.indexOf(")");

                            String restOfExp = expression.substring(Ind2nd + 1);

                            String paren = expression.substring(Ind1st + 1, Ind2nd);
                            sum = eval(paren);

                            expression = Integer.toString(sum) + restOfExp;
                            System.out.println(expression);
                        }
                        else{
                            Operator Opp = operatorStack.pop();
                            Operand op2 = operandStack.pop();
                            Operand op1 = operandStack.pop();
                            operandStack.push(Opp.execute(op1, op2));
                        }

                    }

                    operatorStack.push( newOperator );
                }
            }
        }

        // Control gets here when we've picked up all of the tokens; you must add
        // code to complete the evaluation - consider how the code given here
        // will evaluate the expression 1+2*3
        // When we have no more tokens to scan, the operand stack will contain 1 2
        // and the operator stack will have + * with 2 and * on the top;
        // In order to complete the evaluation we must empty the stacks (except
        // the init operator on the operator stack); that is, we should keep
        // evaluating the operator stack until empty
        // Suggestion: create a method that takes an operator as argument and
        // then executes the while loop; also, move the stacks out of the main
        // method

        while (operatorStack.size() > 0)
        {
            Operator curOpp = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push(curOpp.execute(op1, op2));
        }

        Operand total = operandStack.pop();
        res = total.getValue();

        return res;
    }

}