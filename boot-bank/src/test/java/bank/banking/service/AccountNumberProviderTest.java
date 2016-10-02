/**
 * 
 */
package bank.banking.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import bank.AbstractServiceIntegrationTest;
import bank.banking.data.AccountNumber;
import bank.banking.data.AccountNumberProvider;

/**
 * @author Fabian Krüger
 *
 */
public class AccountNumberProviderTest extends AbstractServiceIntegrationTest {
	
	@Autowired
	private AccountNumberProvider provider;
	
	@Test
	public void createAccountNumber() throws Exception {
		AccountNumber abstractAccountNumber = provider.createAccountNumber();
		System.out.println(abstractAccountNumber);
	}
}
