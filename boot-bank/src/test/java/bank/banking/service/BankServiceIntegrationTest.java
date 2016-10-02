/**
 * 
 */
package bank.banking.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import bank.AbstractServiceIntegrationTest;
import bank.banking.data.AccountSettings;
import bank.banking.data.AccountType;
import bank.banking.data.BankAccount;
import bank.banking.service.BankingService;

/**
 * @author Fabian Krüger
 *
 */
public class BankServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private BankingService bankServiceImpl;

    @Test
    public void createAccount() throws Exception {
        AccountSettings accountSettings = new AccountSettings(new BigDecimal(100), AccountType.BANK_ACCOUNT);
        BankAccount account = bankServiceImpl.createAccount(accountSettings);
        Assert.assertEquals("0000600100", account.getAccountNumber().getValue());
    }
}