package bank.banking.data;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Fabian Krüger
 *
 */
public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {

    BankAccount findAccountByAccountNumber(AccountNumber abstractAccountNumber);

}
