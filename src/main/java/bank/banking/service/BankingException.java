package bank.banking.service;

/**
 * @author Fabian Krüger
 *
 */
public class BankingException extends Exception {

    private static final long serialVersionUID = -8375085702478404960L;

    public BankingException(final String message) {
        super(message);
    }

}
