package bank.banking.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fabian Krüger
 *
 */
@Service
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

    /**
     * @param repository
     */
    public void setAccountNumberRepository(AccountNumberRepository repository) {
      this.accountNumberRepository = repository;
    }

}
