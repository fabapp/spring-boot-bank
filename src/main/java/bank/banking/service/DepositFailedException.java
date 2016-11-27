package bank.banking.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Fabian Krüger
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Deposit failed")
public class DepositFailedException extends BankingException {

    private static final long serialVersionUID = -7900046892418711928L;

    public DepositFailedException(final String string, final Throwable e) {
        super(string, e);
    }

}
