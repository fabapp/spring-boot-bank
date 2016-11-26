package bank.banking.service;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountSettings;
import bank.banking.data.BankAccount;

/**
 * @author Fabian Krüger
 *
 */
public interface BankAccountFactory {

    BankAccount createAccount(AccountSettings settings, AccountNumber abstractAccountNumber);

}
