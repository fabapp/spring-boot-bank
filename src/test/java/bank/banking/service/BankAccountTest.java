/**
 * 
 */
package bank.banking.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import bank.banking.data.AccountNumber;
import bank.banking.data.BankAccount;

/**
 * @author Fabian Krüger
 *
 */
public class BankAccountTest {
	
	private BankAccount account;

	@Before
	public void setup() {
		account = new BankAccount(new AccountNumber("100"));
	}
	@Test
	public void bookPositiveAmountShouldSucceed() throws Exception {
		account.book(new BigDecimal("100.234"));
		assertEquals("100.234", account.getBalance().toPlainString());
	}
	
	@Test
	public void bookNegativeAmountShouldSucceedWitSufficientBalance() throws Exception {
		account.book(new BigDecimal("100.234"));
		account.book(new BigDecimal("1.234").negate());
		assertEquals(99.0, account.getBalance().doubleValue(), 0.0);
	}
	
	@Test
	public void bookNegativeAmountSameAsBalanceShouldSucceed() throws Exception {
		account.book(new BigDecimal("100.234"));
		account.book(new BigDecimal("100.234").negate());
		assertEquals(0, account.getBalance().doubleValue(), 0.0);
	}
	
	@Test(expected=InsufficientBalanceException.class)
	public void bookNegativeAmountWithInsufficientBalanceShouldFail() throws Exception {
		account.book(new BigDecimal("100.234"));
		account.book(new BigDecimal("100.2341").negate());
		assertEquals(99.0, account.getBalance().doubleValue(), 0.0);
	}
}
