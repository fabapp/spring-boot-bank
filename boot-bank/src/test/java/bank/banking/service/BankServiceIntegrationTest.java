/**
 * 
 */
package bank.banking.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import bank.AbstractServiceIntegrationTest;
import bank.banking.data.AccountSettings;
import bank.banking.data.AccountType;
import bank.banking.data.BankAccount;

/**
 * @author Fabian Krüger
 *
 */
public class BankServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private BankingService bankServiceImpl;

    @Test
    @DirtiesContext
    public void createAccount() throws Exception {
        BigDecimal initialBalance = new BigDecimal(100);
		AccountSettings accountSettings = new AccountSettings(initialBalance, AccountType.BANK_ACCOUNT);
        BankAccount account = bankServiceImpl.createAccount(accountSettings);
        Assert.assertNotNull(account.getAccountNumber().getValue());
        Assert.assertEquals(initialBalance, account.getBalance());
    }
}