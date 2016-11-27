/**
 * 
 */
package bank.banking.data;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountNumberProvider;
import bank.banking.data.AccountNumberRepository;

import static org.mockito.Mockito.*;

/**
 * @author Fabian Krüger
 *
 */
@RunWith(SpringRunner.class)
public class AccountNumberProviderTest {
	
	private AccountNumberProvider accountNumberProvider;
	@Mock
	private AccountNumberRepository accountNumberRepository;
	
	@Before
	public void setUp() throws IllegalArgumentException, IllegalAccessException {
		accountNumberProvider = new AccountNumberProvider();
		Field field = ReflectionUtils.findField(AccountNumberProvider.class, "accountNumberRepository");
		field.setAccessible(true);
		field.set(accountNumberProvider, accountNumberRepository);
//		accountNumberProvider.
	}
	
	@Test
	public void testCreateAccountNumber() throws Exception {
		AccountNumber accountNumber = new AccountNumber("100");
		when(accountNumberRepository.save(any(AccountNumber.class))).thenReturn(accountNumber);
		AccountNumber abstractAccountNumber = accountNumberProvider.createAccountNumber();
		assertEquals("0000000100", abstractAccountNumber.getValue());
	}
}
