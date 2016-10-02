package bank.banking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountSettings;
import bank.banking.data.BankAccount;
import bank.banking.service.BankingService;
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
    public BankAccount findAccount(@PathVariable("accountNumber") final String accountNumberStr) throws MissingAccountException {
        AccountNumber abstractAccountNumber = new AccountNumber(accountNumberStr);
        BankAccount bankAccount = bankingService.findAccount(abstractAccountNumber);
        return bankAccount;
    }

    @Autowired
    public void setBankService(final BankingService bankingService) {
        this.bankingService = bankingService;
    }

}
