/**
 * 
 */
package bank.banking.data;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import bank.banking.service.BankAccountFactory;
import bank.banking.service.BankAccountFactoryImpl;

/**
 * @author Fabian Krüger
 *
 */
public class BankAccountFactoryImplTest {
	@Test
	public void createAccountTest() throws Exception {
		BankAccountFactory accountFactory = new BankAccountFactoryImpl();
		BigDecimal balance = new BigDecimal("100.90");
		AccountSettings accountSettings = new AccountSettings(balance, AccountType.BANK_ACCOUNT);
		AccountNumber abstractAccountNumber = new AccountNumber(1);
		BankAccount account = accountFactory.createAccount(accountSettings, abstractAccountNumber);
		Assert.assertEquals(balance, account.getBalance());
		Assert.assertEquals("0000000001", account.getAccountNumber().getValue());
	}

}
