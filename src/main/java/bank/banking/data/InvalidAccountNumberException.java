/**
 * 
 */
package bank.banking.data;


/**
 * @author Fabian Krüger
 *
 */
public class InvalidAccountNumberException extends Exception {

  private static final long serialVersionUID = 5055169311546580778L;

  public InvalidAccountNumberException(String message) {
    super(message);
  }

}
