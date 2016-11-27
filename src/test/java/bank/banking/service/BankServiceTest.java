/**
 * 
 */
package bank.banking.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountNumberProvider;
import bank.banking.data.AccountSettings;
import bank.banking.data.AccountType;
import bank.banking.data.BankAccount;
import bank.banking.data.BankAccountRepository;

/**
 * @author Fabian Krüger
 *
 */
public class BankServiceTest {

  private BankingServiceImpl sut;

  private BankAccountRepository accountRepository;

  private AccountNumberProvider accountNumberProvider;

  private BankAccountFactory bankAccountFactory;

  @Before
  public void setUp() {
    sut = new BankingServiceImpl();
    accountRepository = mock(BankAccountRepository.class);
    sut.setAccountRepository(accountRepository);
    accountNumberProvider = mock(AccountNumberProvider.class);
    sut.setAccountNumberProvider(accountNumberProvider);
    bankAccountFactory = mock(BankAccountFactoryImpl.class);
    sut.setBankAccountFactory(bankAccountFactory);
  }

  @Test
  public void createAccount() throws Exception {
    AccountNumber accountNumber = new AccountNumber(1);
    BigDecimal initialBalance = new BigDecimal(1000);
    AccountType accountType = AccountType.BANK_ACCOUNT;
    BankAccount account = new BankAccount(accountNumber, initialBalance);
    AccountSettings accountSettings = new AccountSettings(initialBalance, accountType);

    when(accountNumberProvider.createAccountNumber()).thenReturn(accountNumber);
    when(bankAccountFactory.createAccount(accountSettings, accountNumber)).thenReturn(account);
    when(accountRepository.save(account)).thenReturn(account);

    BankAccount bankAccount = sut.createAccount(accountSettings);

    assertEquals(accountNumber, bankAccount.getAccountNumber());
    assertEquals(initialBalance, bankAccount.getBalance());
    verify(accountNumberProvider).createAccountNumber();
    verify(accountRepository).save(account);
  }

  @Test
  public void deposit() throws Exception {
    AccountNumber abstractAccountNumber = new AccountNumber(123);
    BigDecimal balance = new BigDecimal(100);
    BigDecimal depositAmount = new BigDecimal(100);
    BankAccount bankAccount = new BankAccount(abstractAccountNumber, balance);
    when(accountRepository.findAccountByAccountNumber(abstractAccountNumber)).thenReturn(bankAccount);
    when(accountRepository.save(bankAccount)).thenReturn(bankAccount);

    sut.deposit(abstractAccountNumber, depositAmount);

    assertEquals(balance.add(depositAmount), bankAccount.getBalance());
    verify(accountRepository).findAccountByAccountNumber(abstractAccountNumber);
    verify(accountRepository).save(bankAccount);
  }

  @Test(expected = DepositFailedException.class)
  public void depositToUnknownAccountShouldFail() throws Exception {
    AccountNumber abstractAccountNumber = new AccountNumber(123);
    when(accountRepository.findAccountByAccountNumber(abstractAccountNumber)).thenReturn(null);
    BigDecimal depositAmount = new BigDecimal(100);

    sut.deposit(abstractAccountNumber, depositAmount);
  }

  @Test
  public void withdrawalWithSufficientBalanceShouldSucceed() throws Exception {
    AccountNumber abstractAccountNumber = new AccountNumber(123);
    BigDecimal balance = new BigDecimal(100);
    BankAccount bankAccount = new BankAccount(abstractAccountNumber, balance);
    when(accountRepository.findAccountByAccountNumber(abstractAccountNumber)).thenReturn(bankAccount);
    BigDecimal amount = new BigDecimal(100);
    BankAccount bankAccount2 = sut.withdrawal(abstractAccountNumber, amount);
    assertEquals(0, bankAccount2.getBalance().intValue());
  }

  @Test(expected = WithdrawalFailedException.class)
  public void withdrawalWithInSufficientBalanceShouldFail() throws Exception {
    AccountNumber abstractAccountNumber = new AccountNumber(123);
    BigDecimal balance = new BigDecimal(100.001);
    BankAccount bankAccount = new BankAccount(abstractAccountNumber, balance);
    when(accountRepository.findAccountByAccountNumber(abstractAccountNumber)).thenReturn(bankAccount);
    BigDecimal amount = new BigDecimal(100.0011);
    sut.withdrawal(abstractAccountNumber, amount);
  }

  @Test(expected = WithdrawalFailedException.class)
  public void withdrawalWithNonExistentAccountShouldFail() throws Exception {
    AccountNumber abstractAccountNumber = new AccountNumber(123);
    when(accountRepository.findAccountByAccountNumber(abstractAccountNumber)).thenReturn(null);
    BigDecimal amount = new BigDecimal(100.0011);
    sut.withdrawal(abstractAccountNumber, amount);
  }
}
