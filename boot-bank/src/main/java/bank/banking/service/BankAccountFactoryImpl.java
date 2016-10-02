package bank.banking.service;

import org.springframework.stereotype.Component;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountSettings;
import bank.banking.data.BankAccount;

/**
 * @author Fabian Krüger
 *
 */
@Component
public class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    public BankAccount createAccount(final AccountSettings settings, final AccountNumber abstractAccountNumber) {
        BankAccount bankAccount;
        switch (settings.getAccountType()) {
        default:
            bankAccount = new BankAccount(abstractAccountNumber, settings.getInitialBalance());
            break;
        }
        return bankAccount;
    }
}
