package bank.banking.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.EqualsAndHashCode;

/**
 * @author Fabian Krüger
 *
 */
// smell: db generated primary key used for comparison in equals and hashCode.
// Here it should be no problem, because the AccountNumber is passed to JPA
// before it is used as key in a BankAccout or elsewhere.
@EqualsAndHashCode(of = "accountNumber")
@Entity
@SequenceGenerator(name = "accnr", initialValue = AccountNumber.INITIAL_VALUE, allocationSize = AccountNumber.ALLOCATION_SIZE, sequenceName = "accnr")
public class AccountNumber implements Serializable {

    static final int ALLOCATION_SIZE = 600100;

    static final int INITIAL_VALUE = 100;

    private static final long serialVersionUID = 1887172166562310307L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accnr")
    @Id
    private int accountNumber;

    AccountNumber() {
    }

    public AccountNumber(final String accountNumber) {
        this.accountNumber = Integer.parseInt(accountNumber);
    }

    public String getValue() {
        return String.format("%010d", accountNumber);
    }

    @Override
    public String toString() {
        return getValue();
    }

}
