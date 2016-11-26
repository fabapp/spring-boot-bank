package bank.banking.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Fabian Krüger
 *
 */
@Repository
public class AccountNumberProvider {

    @Autowired
    private AccountNumberRepository accountNumberRepository;

    /**
     * @return a new AccountNumber
     */
    public AccountNumber createAccountNumber() {
        AccountNumber accountNumber = new AccountNumber();
        accountNumber = accountNumberRepository.save(accountNumber);
        return accountNumber;
    }

}
