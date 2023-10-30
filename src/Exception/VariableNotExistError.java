package Exception;

/**
 * @brief   This class is used to represent an error when a variable does not exist
 * @details This class is used to represent an error when a variable does not exist
 *          It extends the Exception class
 */
public class VariableNotExistError extends Exception {
    /**
     * @brief   Constructor of the VariableNotExistError class
     * @details It overrides the Exception constructor
     * @param message   The message of the error
     */
    public VariableNotExistError(String message) {
        super(message);
    }
}
