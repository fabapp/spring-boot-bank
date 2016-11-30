/**
 * 
 */
package bank.banking.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import bank.banking.data.AccountSettings;
import bank.banking.data.AccountType;
import bank.banking.data.BankAccount;
import categories.IntegrationTest;

/**
 * @author Fabian Krüger
 *
 */
@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankServiceIntegrationTest {

  @Autowired
  private BankingService bankServiceImpl;

  @Test
  public void createAccount() throws Exception {
    BigDecimal initialBalance = new BigDecimal(100);
    AccountSettings accountSettings = new AccountSettings(initialBalance, AccountType.BANK_ACCOUNT);
    BankAccount account = bankServiceImpl.createAccount(accountSettings);
    Assert.assertEquals(initialBalance, account.getBalance());
    Assert.assertNotNull(account.getAccountNumber().getValue());
  }
}