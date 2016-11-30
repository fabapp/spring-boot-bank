/**
 * 
 */
package bank.banking.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import bank.banking.data.AccountNumber;
import bank.banking.data.AccountNumberProvider;
import categories.IntegrationTest;

/**
 * @author Fabian Krüger
 *
 */
@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@Rollback(true)
@Transactional
@SpringBootTest
// A new sequence which starts from 1 is required
// @DirtiesContext triggers recreation of in memory database which result in a new sequence table.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountNumberProviderIntegrationTest { // extends AbstractServiceIntegrationTest {

  @Autowired
  private AccountNumberProvider provider;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  TransactionTemplate transactionTemplate;
  
  @Before
  public void setup() {

  }
  
  @Test
  public void createAccountNumberShouldCreateUniqueAccountNumberOnEachCall() throws Exception {
    AccountNumber abstractAccountNumber1 = provider.createAccountNumber();
    AccountNumber abstractAccountNumber2 = provider.createAccountNumber();
    AccountNumber abstractAccountNumber3 = provider.createAccountNumber();
    Assert.assertEquals("0000600100", abstractAccountNumber1.getValue());
    Assert.assertEquals("0000600101", abstractAccountNumber2.getValue());
    Assert.assertEquals("0000600102", abstractAccountNumber3.getValue());
  }
  
}
