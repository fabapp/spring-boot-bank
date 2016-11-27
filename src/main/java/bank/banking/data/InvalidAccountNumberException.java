/**
 * 
 */
package bank.banking.data;

/**
 * @author Fabian Krüger
 *
 */
public class InvalidAccountNumberException extends RuntimeException {

  private static final long serialVersionUID = 5055169311546580778L;

  public InvalidAccountNumberException() {
    super();
  }

  public InvalidAccountNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public InvalidAccountNumberException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidAccountNumberException(String message) {
    super(message);
  }

  public InvalidAccountNumberException(Throwable cause) {
    super(cause);
  }

}
