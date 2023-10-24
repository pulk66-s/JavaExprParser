package Expression.Minimal;

import Context.Environnement;
import Context.StatusCode;
import Exception.VariableNotExistError;

public class VariableExpression extends MinimalExpression {
    private String name;

    public StatusCode parse(Environnement env) {
        this.name = env.getExpression().trim();

        if (!this.name.matches("[a-zA-Z]+")) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    public Double evaluate() throws VariableNotExistError {
        return Environnement.getVariable(name);
    }

    public StringBuilder toStringBuilder() {
        return new StringBuilder(this.name);
    }
}
