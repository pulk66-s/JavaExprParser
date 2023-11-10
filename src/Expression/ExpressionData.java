package Expression;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import Expression.Operation.MultiplicationExpression;

/**
 * @brief   This class is used to represent the datas of an expression, 
 *          especially the constant and the variables
 *          E.g: 2x + 3y + 4z + 5
 *              -> constant = 5
 *              -> variables = {x: 2, y: 3, z: 4}
 */
public class ExpressionData {
    private Double constant;
    private HashMap<String, Double> variables;

    /**
     * @brief           The constructor
     * @param constant  The constant of the expression
     * @param variables The variables of the expression
     */
    public ExpressionData(Double constant, HashMap<String, Double> variables) {
        this.constant = constant;
        this.variables = variables;
    }

    /**
     * @brief   Delete the variables with a value of 0.0
     */
    private void deleteZeros() {
        this.variables.entrySet().removeIf(entry -> entry.getValue() == 0.0);
    }

    /**
     * @brief   Delete the duplicates variables like "x*y" and "y*x"
     */
    private void deleteDuplicates() {
        HashMap<String, Double> newVariables = new HashMap<String, Double>();

        for (String key : this.variables.keySet()) {
            List<String> splittedElements = Arrays.asList(key.split("\\*"));
            splittedElements.sort(null);
            String newName = String.join("*", splittedElements);

            if (newVariables.containsKey(newName)) {
                newVariables.put(newName, newVariables.get(newName) + this.variables.get(key));
            } else {
                newVariables.put(newName, this.variables.get(key));
            }
        }
        this.variables = newVariables;
    }

    /**
     * @brief   Create a multiplication expression with a variable and a constant
     * @param   variable    The variable
     * @param   constant    The constant
     * @return  The multiplication expression
     */
    public MultiplicationExpression createMultiplication(String key, Double value) {
        HashMap<String, Double> variables = new HashMap<String, Double>();
        List<String> splittedElements = Arrays.asList(key.split("\\*"));
        MultiplicationExpression mul = ArithmeticExpressionFactory.createMultiplication(Optional.empty(), Optional.empty());
        MultiplicationExpression tmp = mul;

        for (String k : splittedElements) {
            if (!variables.containsKey(k)) {
                variables.put(k, (double) Collections.frequency(splittedElements, k));
            }
        }

        String lastElement = variables.keySet().toArray()[variables.size() - 1].toString();

        tmp.setLeft(MinimalExpressionFactory.createConstant(value));
        for (String k : variables.keySet()) {
            MultiplicationExpression newMul = ArithmeticExpressionFactory.createMultiplication(
                Optional.empty(),
                Optional.empty()
            );

            if (variables.get(k) == 1) {
                if (k.equals(lastElement)) {
                    tmp.setRight(MinimalExpressionFactory.createVariable(k));
                } else {
                    newMul.setLeft(MinimalExpressionFactory.createVariable(k));
                }
            } else {
                if (k.equals(lastElement)) {
                    tmp.setRight(
                        ArithmeticExpressionFactory.createPower(
                            Optional.of(MinimalExpressionFactory.createVariable(k)),
                            Optional.of(MinimalExpressionFactory.createConstant(variables.get(k)))
                        )
                    );
                } else {
                    newMul.setLeft(
                        ArithmeticExpressionFactory.createPower(
                            Optional.of(MinimalExpressionFactory.createVariable(k)),
                            Optional.of(MinimalExpressionFactory.createConstant(variables.get(k)))
                        )
                    );
                }
            }
            if (!k.equals(lastElement)) {
                tmp.setRight(newMul);
                tmp = newMul;
            }
        }
        return mul;
    }

    /**
     * @brief   Getter of the constant
     * @return  The constant
     */
    public ArithmeticExpression toExpression() {
        OperationExpression add = ArithmeticExpressionFactory.createAddition(Optional.empty(), Optional.empty());
        OperationExpression tmp = add;

        this.deleteZeros();
        this.deleteDuplicates();
        if (this.constant.equals(0.0) && this.variables.isEmpty()) {
            return MinimalExpressionFactory.createConstant(0.0);
        } else if (!this.constant.equals(0.0)) {
            tmp.setLeft(MinimalExpressionFactory.createConstant(this.constant));
        }

        String lastElement =  this.variables.size() == 0 ? "" : this.variables.keySet().toArray()[this.variables.size() - 1].toString();

        for (String key : this.variables.keySet()) {
            if (key.equals(lastElement)) {
                ArithmeticExpression lastElem;

                if (this.variables.get(key) == 1) {
                    lastElem = MinimalExpressionFactory.createVariable(key);
                } else {
                    lastElem = this.createMultiplication(key, this.variables.get(key));
                }
                if (!tmp.getLeft().isPresent()) {
                    return lastElem;
                } else {
                    tmp.setRight(lastElem);
                }
            } else {
                if (!tmp.getLeft().isPresent()) {
                    if (this.variables.get(key) == 1) {
                        tmp.setLeft(MinimalExpressionFactory.createVariable(key));
                    } else {
                        tmp.setLeft(this.createMultiplication(key, this.variables.get(key)));
                    }
                } else {
                    ArithmeticExpression leftAdd;

                    if (this.variables.get(key) == 1) {
                        leftAdd = MinimalExpressionFactory.createVariable(key);
                    } else {
                        leftAdd = this.createMultiplication(key, this.variables.get(key));
                    }

                    OperationExpression newAdd = ArithmeticExpressionFactory.createAddition(
                            Optional.of(leftAdd),
                            Optional.empty()
                        );

                    tmp.setRight(newAdd);
                    tmp = newAdd;
                }
            }
        }
        return add;
    }

    /**
     * @brief   Getter of the constant
     * @return  The constant
     */
    public Double constant() {
        return this.constant;
    }

    /**
     * @brief   Getter of the variables
     * @return  The variables
     */
    public HashMap<String, Double> variables() {
        return this.variables;
    }
}
