package bank.banking.data;

import org.springframework.data.repository.Repository;

/**
 * @author Fabian Krüger
 *
 */
public interface AccountNumberRepository extends Repository<AccountNumber, Integer> {

    AccountNumber save(AccountNumber abstractAccountNumber);
}
