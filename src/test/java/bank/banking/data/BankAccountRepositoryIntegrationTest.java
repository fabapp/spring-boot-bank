/**
 * 
 */
package bank.banking.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import bank.AbstractPersistenceIntegrationTest;

/**
 * @author Fabian Krüger
 *
 */
public class BankAccountRepositoryIntegrationTest extends AbstractPersistenceIntegrationTest {
	@Autowired
	private BankAccountRepository accountRepository;

	@Autowired
	private AccountNumberRepository accountNumberRepository;

	@Test
	public void findAccountByAccountNumber() throws Exception {
		AccountNumber abstractAccountNumber = accountNumberRepository.save(new AccountNumber());
		BankAccount account = new BankAccount(abstractAccountNumber);
		account = accountRepository.save(account);
		assertEquals(abstractAccountNumber.getValue(), account.getAccountNumber().getValue());
		assertSame(1, account.getId());
		BankAccount accountFound = accountRepository.findAccountByAccountNumber(abstractAccountNumber);
		assertEquals(account, accountFound);
	}
}
