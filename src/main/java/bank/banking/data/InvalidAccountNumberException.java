/**
 * 
 */
package bank.banking.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Fabian Krüger
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid AccoutNumber given.")
public class InvalidAccountNumberException extends Exception {

  private static final long serialVersionUID = 5055169311546580778L;

  public InvalidAccountNumberException(String message) {
    super(message);
  }

}
