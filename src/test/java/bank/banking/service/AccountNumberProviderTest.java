/**
 * 
 */
package bank.banking.service;

import static org.mockito.Mockito.mock;

import org.apache.el.util.ReflectionUtil;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountNumberProvider;
import bank.banking.data.AccountNumberRepository;

/**
 * @author Fabian Krüger
 *
 */
public class AccountNumberProviderTest {
	
	private AccountNumberProvider provider;
	private AccountNumberRepository repository;
	
	@Before
	public void setuo(){
	  repository = mock(AccountNumberRepository.class);
	  provider = new AccountNumberProvider();
	  provider.setAccountNumberRepository(repository);
	}
	
	@Test
	public void createAccountNumber() throws Exception {
	  AccountNumber managedAccountNumber = new AccountNumber(1);
	  when(repository.save(any(AccountNumber.class))).thenReturn(managedAccountNumber);
		AccountNumber returnedAccountNumber = provider.createAccountNumber();
		Assert.assertSame(managedAccountNumber, returnedAccountNumber);
		verify(repository).save(any(AccountNumber.class));
		System.out.println(returnedAccountNumber);
	}
}
