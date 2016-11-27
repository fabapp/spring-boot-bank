/**
 * 
 */
package bank.banking.data;

import org.junit.Test;

/**
 * @author Fabian Krüger
 *
 */
public class AccountNumberTest {
  @Test(expected=InvalidAccountNumberException.class)
  public void invalidAccountNumberSHouldThrowException() throws Exception {
    new AccountNumber(0);
  }
}
