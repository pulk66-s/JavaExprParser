package Expression;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Exception.VariableNotExistError;
import Parser.Parsing;

public class DeclarationExpression extends ArithmeticExpression {
    private String name;
    private ArithmeticExpression value;

    public StatusCode parse(Environnement env) throws SyntaxError {
        int equalOpIndex = env.findChar('=');

        if (equalOpIndex < 0) {
            return StatusCode.FAILURE;
        }

        String leftExpr = env.getExpression().substring(0, equalOpIndex).trim();
        String rightExpr = env.getExpression().substring(equalOpIndex + 1);

        if (!leftExpr.matches("[a-zA-Z]+")) {
            return StatusCode.FAILURE;
        }
        this.name = leftExpr;
        this.value = new Parsing().parse(rightExpr);
        return StatusCode.SUCCESS;
    }

    public Double evaluate() throws VariableNotExistError {
        Environnement.setVariable(this.name, this.value.evaluate());
        return 0.0;
    }

    public void simplify() throws VariableNotExistError {
        this.value.simplify();
    }

    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.name);
        sb.append(" = ");
        sb.append(this.value.toStringBuilder());
        return sb;
    }
}
