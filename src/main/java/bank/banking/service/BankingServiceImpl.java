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
@Transactional
@Service
public class BankingServiceImpl implements BankingService {

    private AccountNumberProvider accountNumberProvider;

    private BankAccountRepository bankAccountRepository;

    private BankAccountFactory bankAccountFactory;

    public BankingServiceImpl() {
    }

    @Override
    public BankAccount createAccount(final AccountSettings settings) {
        AccountNumber accountNumber = accountNumberProvider.createAccountNumber();
        BankAccount bankAccount = bankAccountFactory.createAccount(settings, accountNumber);
        bankAccount = bankAccountRepository.save(bankAccount);
        return bankAccount;
    }

    @Override
    public BankAccount deposit(final AccountNumber accountNumber, final BigDecimal amount) throws DepositFailedException {
        BankAccount bankAccount;
        try {
            bankAccount = findAccount(accountNumber);
            bankAccount.book(amount);
            bankAccountRepository.save(bankAccount);
            return bankAccount;
        } catch (MissingAccountException | InsufficientBalanceException e) {
            throw new DepositFailedException("Depsosit failed", e);
        }
    }

    @Override
    public BankAccount withdrawal(final AccountNumber accountNumber, final BigDecimal amount) throws MissingAccountException, InsufficientBalanceException {
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
