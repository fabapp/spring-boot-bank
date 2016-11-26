package bank.banking.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import bank.banking.data.AccountNumber;

/**
 * @author Fabian Krüger
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such BankAccount")
public class MissingAccountException extends BankingException {

    private static final long serialVersionUID = 2499738885124126446L;

    public MissingAccountException(final AccountNumber abstractAccountNumber) {
        super("Account: [" + abstractAccountNumber + "] could not be found.");
    }
}
