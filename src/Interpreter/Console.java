package Interpreter;

import java.util.HashMap;
import java.util.function.Consumer;

public class Console {
    private Boolean running = true;
    private HashMap<String, Consumer<Void>> commands = new HashMap<String, Consumer<Void>>() {{
        put("exit", (Void) -> runExit());
    }};

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
            System.out.println("You entered: " + input);
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
                this.commands.get(command).accept(null);
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

    }

    /**
     * @brief   Exit the console
     * @details This method is called when the user enters the command "exit"
     *          in the console. It set the attribute "running" to false, which
     *          will stop the console loop.
     */
    private void runExit() {
        this.running = false;
    }
}
