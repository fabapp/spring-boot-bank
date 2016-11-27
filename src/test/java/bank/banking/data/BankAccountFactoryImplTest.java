/**
 * 
 */
package bank.banking.data;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountSettings;
import bank.banking.data.AccountType;
import bank.banking.data.BankAccount;
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
		AccountNumber abstractAccountNumber = new AccountNumber("1");
		BankAccount account = accountFactory.createAccount(accountSettings, abstractAccountNumber);
		Assert.assertEquals(balance, account.getBalance());
	}
}
