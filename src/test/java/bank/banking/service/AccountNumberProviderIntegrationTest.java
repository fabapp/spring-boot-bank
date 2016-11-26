/**
 * 
 */
package bank.banking.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import bank.AbstractServiceIntegrationTest;
import bank.banking.data.AccountNumber;
import bank.banking.data.AccountNumberProvider;

/**
 * @author Fabian Krüger
 *
 */
public class AccountNumberProviderIntegrationTest extends AbstractServiceIntegrationTest {
	
	@Autowired
	private AccountNumberProvider provider;
	
	@Test
	public void createAccountNumberShouldCreateUniqueAccountNumberOnEachCall() throws Exception {
		AccountNumber abstractAccountNumber1 = provider.createAccountNumber();
		AccountNumber abstractAccountNumber2 = provider.createAccountNumber();
		AccountNumber abstractAccountNumber3 = provider.createAccountNumber();
		Assert.assertEquals("0000600100", abstractAccountNumber1.getValue());
		Assert.assertEquals("0000600101", abstractAccountNumber2.getValue());
		Assert.assertEquals("0000600102", abstractAccountNumber3.getValue());
	}
}
