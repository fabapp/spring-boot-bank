package bank.banking.service;

/**
 * @author Fabian Krüger
 *
 */
public class WithdrawalFailedException extends Exception {

    private static final long serialVersionUID = -227128244203718194L;

    public WithdrawalFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
