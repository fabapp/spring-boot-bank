/**
 * 
 */
package bank.banking.service;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import bank.banking.data.AccountNumber;

/**
 * @author Fabian Krüger
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Insufficient Funds.")
public class InsufficientFundsException extends Exception {

    private static final long serialVersionUID = -1742784769383421410L;

    public InsufficientFundsException(final AccountNumber accountNumber, final BigDecimal amount) {
        super("Could not transfer " + amount + ". Insufficient balance on account: " + accountNumber.getValue() + ".");
    }
}
