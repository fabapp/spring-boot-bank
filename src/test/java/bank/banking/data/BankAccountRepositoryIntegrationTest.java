/**
 * 
 */
package bank.banking.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import bank.AbstractPersistenceIntegrationTest;
import bank.AbstractServiceIntegrationTest;
import bank.banking.data.AccountNumber;
import bank.banking.data.AccountNumberProvider;
import bank.banking.data.AccountNumberRepository;
import bank.banking.data.BankAccount;
import bank.banking.data.BankAccountRepository;

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
