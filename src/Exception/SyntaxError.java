package Exception;

/**
 * @brief   This class is used to represent a syntax error
 * @details This class is used to represent a syntax error
 *          It extends the Exception class
 */
public class SyntaxError extends Exception {
    /**
     * @brief   Constructor of the SyntaxError class
     * @details It overrides the Exception constructor
     * @param message   The message of the error
     */
    public SyntaxError(String message) {
        super(message);
    }
}
