package Interpreter;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import Context.Environnement;
import Exception.SyntaxError;
import Expression.ArithmeticExpression;
import Expression.ArithmeticExpressionFactory;
import Expression.ExpressionData;
import Expression.MinimalExpressionFactory;

/**
 * @brief   This class is used to represent a console
 */
public class Console {

    // Boolean to check if the program is running or not
    private Boolean running = true;

    // Map that contains the special commands
    private HashMap<String, Function<Void, Void>> commands = new HashMap<String, Function<Void, Void>>() {{
        put("exit", Console.this::runExit);
    }};

    // The current environnement of execution
    private Environnement env = new Environnement();

    /**
     * @brief   Run the console
     * @details This method is called when the program start, it will run multiples
     *          commands until the command "exit" is entered.
     *          If the comman is a special command, it will run the associated
     *          method, otherwise it will run the expression parser.
     */
    public void run() {
        System.out.println("Starting console...");
        while (this.running) {
            System.out.println("Enter your command / expr: ");
            String input = System.console().readLine();
            this.runCommand(input);
        }
    }

    /**
     * @brief       Run a command
     * @param input The command to run
     * @details     Once a command is entered, this method will check if it is a
     *              special command and run it if it is the case, otherwise it will
     *              run the expression parser.
     */
    private void runCommand(String input) {
        for (String command : this.commands.keySet()) {
            if (input.startsWith(command)) {
                this.commands.get(command).apply(null);
                return;
            }
        }
        this.runExpression(input);
    }

    /**
     * @brief       Run the expression parser
     * @param input The expression to parse
     */
    private void runExpression(String input) {
        ArithmeticExpression expr;
        Optional<ArithmeticExpression> exprRes;

        this.env.setExpression(input);
        try {
            exprRes = new ArithmeticExpressionFactory().parse(input);

            if (!exprRes.isPresent()) {
                exprRes = new MinimalExpressionFactory().parse(input);
                if (!exprRes.isPresent()) {
                    System.out.println("Can't parse the expression");
                    return;
                }
            }
            System.out.println("Raw epxression " + exprRes);
            Optional<ExpressionData> exprData = exprRes.get().simplify();

            if (!exprData.isPresent()) {
                System.out.println("Can't simplify the expression");
                return;
            }
            System.out.println("Simplified === " + exprData.get().toExpression());
            expr = exprData.get().toExpression();
        } catch (SyntaxError err) {
            System.out.println("You have a syntax problem");
            return;
        }
        System.out.println("Simplified expression: " + expr);

        Optional<Double> r = exprRes.get().evaluate();

        if (r.isPresent()) {
            System.out.println("evaluation result " + r.get());
        } else {
            System.out.println("evaluation: Can't evaluate the expression");
        }
    }

    /**
     * @brief   Exit the console
     * @details This method is called when the user enters the command "exit"
     *          in the console. It set the attribute "running" to false, which
     *          will stop the console loop.
     */
    private Void runExit(Void v) {
        this.running = false;
        return null;
    }
}
