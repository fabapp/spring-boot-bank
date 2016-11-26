/**
 * 
 */
package bank.banking.service;

import java.math.BigDecimal;

import bank.banking.data.AccountNumber;

/**
 * @author Fabian Krüger
 *
 */
public class InsufficientBalanceException extends Exception {
	private static final long serialVersionUID = -1742784769383421410L;

	public InsufficientBalanceException(AccountNumber accountNumber, BigDecimal amount) {
		super("Could not transfer " + amount + ". Insufficient balance on account: " + accountNumber.getValue() + ".");
	}
}
