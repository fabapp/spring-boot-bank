package bank.banking.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountNumberProvider;
import bank.banking.data.AccountSettings;
import bank.banking.data.BankAccount;
import bank.banking.data.BankAccountRepository;

/**
 * @author Fabian Krüger
 *
 */
@Service
public class BankingServiceImpl implements BankingService {

  @Autowired
  private AccountNumberProvider accountNumberProvider;

  private BankAccountRepository bankAccountRepository;

  private BankAccountFactory bankAccountFactory;

  public BankingServiceImpl() {
  }

  @Override
  @Transactional
  public BankAccount createAccount(final AccountSettings settings) {
    AccountNumber accountNumber = accountNumberProvider.createAccountNumber();
    BankAccount bankAccount = bankAccountFactory.createAccount(settings, accountNumber);
    bankAccount = bankAccountRepository.save(bankAccount);
    return bankAccount;
  }

  @Override
  @Transactional
  public BankAccount deposit(final AccountNumber accountNumber, final BigDecimal amount)
      throws MissingAccountException, /* FIXME: design flaw that we have this exception here: */ InsufficientFundsException {
    BankAccount bankAccount;
    bankAccount = findAccount(accountNumber);
    bankAccount.book(amount);
    bankAccountRepository.save(bankAccount);
    return bankAccount;
  }

  @Override
  @Transactional
  public BankAccount withdrawal(final AccountNumber accountNumber, final BigDecimal amount) throws InsufficientFundsException, MissingAccountException {
    BankAccount account;
    account = findAccount(accountNumber);
    account.book(amount.abs().negate());
    return account;
  }

  @Override
  public BankAccount findAccount(final AccountNumber accountNumber) throws MissingAccountException {
    BankAccount bankAccount = bankAccountRepository.findAccountByAccountNumber(accountNumber);
    if (bankAccount == null) {
      throw new MissingAccountException(accountNumber);
    }
    return bankAccount;
  }

  @Autowired
  public void setAccountRepository(final BankAccountRepository accountRepository) {
    this.bankAccountRepository = accountRepository;
  }

  @Autowired
  public void setAccountNumberProvider(final AccountNumberProvider accountNumberProvider) {
    this.accountNumberProvider = accountNumberProvider;
  }

  @Autowired
  public void setBankAccountFactory(final BankAccountFactory bankAccountFactory) {
    this.bankAccountFactory = bankAccountFactory;
  }
}
