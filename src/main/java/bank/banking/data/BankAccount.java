package bank.banking.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import bank.banking.service.InsufficientBalanceException;
import lombok.EqualsAndHashCode;

/**
 * @author Fabian Krüger
 *
 */
@EqualsAndHashCode(of = "accountNumber")
@Entity
public class BankAccount implements Serializable {

    private static final long serialVersionUID = -3162113160281691025L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private AccountNumber accountNumber;

    private BigDecimal balance = new BigDecimal(0);

    @SuppressWarnings("unused")
    private BankAccount() {
    }

    public BankAccount(final AccountNumber accountNumber) {
        super();
        this.accountNumber = accountNumber;
    }

    public BankAccount(final AccountNumber accountNumber, final BigDecimal balance) {
        super();
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void book(final BigDecimal amount) throws InsufficientBalanceException {
        if (balance.add(amount).doubleValue() >= 0) {
            this.balance = this.balance.add(amount);
        } else {
            throw new InsufficientBalanceException(getAccountNumber(), amount);
        }
    }
}
