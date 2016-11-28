package bank.banking.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import bank.banking.service.InsufficientFundsException;
import lombok.EqualsAndHashCode;

/**
 * @author Fabian Krüger
 *
 */
// @EqualsAndHashCode(of = "accountNumber")
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
    if (accountNumber == null) {
      throw new IllegalArgumentException("Valid account number must be given.");
    }
    this.accountNumber = accountNumber;
  }

  public BankAccount(final AccountNumber accountNumber, final BigDecimal balance) {
    super();
    this.accountNumber = accountNumber;
    this.balance = balance;
  }

  public void book(final BigDecimal amount) throws InsufficientFundsException {
    if (balance.add(amount).doubleValue() >= 0) {
      this.balance = this.balance.add(amount);
    } else {
      throw new InsufficientFundsException(getAccountNumber(), amount);
    }
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BankAccount other = (BankAccount) obj;
    if (accountNumber == null) {
      if (other.accountNumber != null)
        return false;
    } else if (!accountNumber.equals(other.accountNumber))
      return false;
    return true;
  }
  
  

}
