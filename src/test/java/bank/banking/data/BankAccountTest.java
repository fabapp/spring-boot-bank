/**
 * 
 */
package bank.banking.data;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bank.banking.service.InsufficientFundsException;

/**
 * @author Fabian Krüger
 *
 */
public class BankAccountTest {
	
	private BankAccount account;

	@Before
	public void setup() throws InvalidAccountNumberException {
		account = new BankAccount(new AccountNumber(100));
	}
	
	@Test(expected=IllegalArgumentException.class)
  public void nullAccountNumberShouldThrowException() throws Exception {
    new BankAccount(null);
  }
	
	@Test
  public void accountNumberWithSameAccountNumberShouldBeEqual() throws Exception {
    AccountNumber accountNumber = new AccountNumber(1);
    AccountNumber accountNumber2 = new AccountNumber(1);
    BankAccount account = new BankAccount(accountNumber);
    BankAccount account2 = new BankAccount(accountNumber2);
    assertEquals(account, account2);
  }
	
	@Test
  public void accountNumberWithDifferentAccountNumberShouldNotBeEqual() throws Exception {
    AccountNumber accountNumber = new AccountNumber(1);
    AccountNumber accountNumber2 = new AccountNumber(2);
    BankAccount account = new BankAccount(accountNumber);
    BankAccount account2 = new BankAccount(accountNumber2);
    assertNotEquals(account, account2);
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
	
	@Test(expected=InsufficientFundsException.class)
	public void bookNegativeAmountWithInsufficientBalanceShouldFail() throws Exception {
		account.book(new BigDecimal("100.234"));
		account.book(new BigDecimal("100.2341").negate());
		assertEquals(99.0, account.getBalance().doubleValue(), 0.0);
	}
}
