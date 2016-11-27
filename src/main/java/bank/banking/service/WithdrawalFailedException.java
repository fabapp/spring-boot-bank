package bank.banking.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Fabian Krüger
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Withdrawal failed")
public class WithdrawalFailedException extends Exception {

  private static final long serialVersionUID = -227128244203718194L;

  public WithdrawalFailedException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
