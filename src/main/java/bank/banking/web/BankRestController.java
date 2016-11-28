package bank.banking.web;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountSettings;
import bank.banking.data.BankAccount;
import bank.banking.data.InvalidAccountNumberException;
import bank.banking.service.BankingService;
import bank.banking.service.InsufficientFundsException;
import bank.banking.service.MissingAccountException;

/**
 * @author Fabian Krüger
 *
 */
@RequestMapping(value = "/rest/bank")
@RestController
public class BankRestController {

  private BankingService bankingService;

  @RequestMapping(value = "/accounts", method = RequestMethod.POST)
  public BankAccount createAccount(@RequestBody final AccountSettings accountSettings) {
    return bankingService.createAccount(accountSettings);
  }
  
  @RequestMapping(value = "/accounts/{accountNumber}", method = RequestMethod.GET)
  public BankAccount findAccount(@PathVariable("accountNumber") final String accountNumberStr) throws MissingAccountException, InvalidAccountNumberException {
    AccountNumber abstractAccountNumber = new AccountNumber(accountNumberStr);
    BankAccount bankAccount = bankingService.findAccount(abstractAccountNumber);
    return bankAccount;
  }

  @RequestMapping(value = "/accounts/deposit/{accountNumber}", method = RequestMethod.POST)
  public BankAccount deposit(@PathVariable("accountNumber") final String accountNumberStr, final @RequestBody Map<String, String> json)
      throws MissingAccountException, InvalidAccountNumberException, InsufficientFundsException {
    AccountNumber abstractAccountNumber = new AccountNumber(accountNumberStr);
    BankAccount bankAccount = bankingService.deposit(abstractAccountNumber, new BigDecimal(json.get("amount")));
    return bankAccount;
  }

  @RequestMapping(value = "/accounts/withdrawal/{accountNumber}", method = RequestMethod.POST)
  public BankAccount withdrawal(@PathVariable("accountNumber") final String accountNumberStr, final @RequestBody Map<String, String> json)
      throws MissingAccountException, InsufficientFundsException, InvalidAccountNumberException {
    AccountNumber abstractAccountNumber = new AccountNumber(accountNumberStr);
    BankAccount bankAccount;
    bankAccount = bankingService.withdrawal(abstractAccountNumber, new BigDecimal(json.get("amount")));
    return bankAccount;
  }

  @Autowired
  public void setBankService(final BankingService bankingService) {
    this.bankingService = bankingService;
  }

}
