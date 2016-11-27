/**
 * 
 */
package bank.banking.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountSettings;
import bank.banking.data.BankAccount;
import bank.banking.service.BankingService;
import bank.banking.service.MissingAccountException;

/**
 * @author Fabian Krüger
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BankRestController.class)
public class BankRestControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BankingService bankingService;

  private JacksonTester<BankAccount> json;

  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
  }

  @Test
  public void findAccount() throws Exception {
    AccountNumber abstractAccountNumber = new AccountNumber(123);
    BigDecimal balance = new BigDecimal(100);
    BankAccount bankAccount = new BankAccount(abstractAccountNumber, balance);
    when(this.bankingService.findAccount(any(AccountNumber.class))).thenReturn(bankAccount);

    this.mockMvc.perform(get("/rest/bank/accounts/1")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(json.write(bankAccount).getJson()));

    verify(this.bankingService).findAccount(any(AccountNumber.class));
  }

  @Test
  public void findAccountWithUnknownAccountNumberShouldFail() throws Exception {
    AccountNumber abstractAccountNumber = new AccountNumber(1);
    when(this.bankingService.findAccount(any(AccountNumber.class))).thenThrow(new MissingAccountException(abstractAccountNumber));

    String error = this.mockMvc.perform(get("/rest/bank/accounts/1").accept(MediaType.ALL)).andExpect(status().isNotFound()).andReturn().getResponse().getErrorMessage();
    assertEquals("No such BankAccount", error);
    verify(this.bankingService).findAccount(any(AccountNumber.class));
  }

  @Test
  public void createAccountTest() throws Exception {
    AccountNumber abstractAccountNumber = new AccountNumber(123);
    BigDecimal balance = new BigDecimal(100);
    BankAccount bankAccount = new BankAccount(abstractAccountNumber, balance);
    bankAccount.setId(1);

    when(this.bankingService.createAccount(any(AccountSettings.class))).thenReturn(bankAccount);

    BankAccount createBankAccount = new BankAccount(abstractAccountNumber, balance);

    this.mockMvc.perform(post("/rest/bank/accounts").content(json.write(createBankAccount).getJson()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.ALL))
        .andExpect(status().isOk()).andExpect(content().string(json.write(bankAccount).getJson()));

    verify(this.bankingService).createAccount(any(AccountSettings.class));
  }

}
