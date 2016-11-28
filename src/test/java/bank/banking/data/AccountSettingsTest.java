/**
 * 
 */
package bank.banking.data;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Fabian Krüger
 *
 */
public class AccountSettingsTest {
  
  @Test
  public void setInitialBalanceJsonProperty() throws Exception {
   AccountSettings accountSettings = new AccountSettings(null, AccountType.BANK_ACCOUNT);
   double initialBalance = 12.1;
   accountSettings.setInitialBalance(initialBalance);
   Assert.assertEquals(new BigDecimal(initialBalance), accountSettings.getInitialBalance());
  }
}
