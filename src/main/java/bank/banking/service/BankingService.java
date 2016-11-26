package bank.banking.service;

import java.math.BigDecimal;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountSettings;
import bank.banking.data.BankAccount;

/**
 * @author Fabian Krüger
 *
 */
public interface BankingService {
	/**
	 * Blah...
	 */
    BankAccount createAccount(AccountSettings settings);

    BankAccount findAccount(AccountNumber abstractAccountNumber) throws MissingAccountException;

    BankAccount deposit(AccountNumber abstractAccountNumber, BigDecimal amount) throws DepositFailedException;

    BankAccount withdrawal(AccountNumber abstractAccountNumber, BigDecimal amount) throws DepositFailedException, MissingAccountException, WithdrawalFailedException;

}
