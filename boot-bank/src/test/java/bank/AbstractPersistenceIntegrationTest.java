/**
 * 
 */
package bank;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import bank.BootbankApplication;

/**
 * @author Fabian Krüger
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public abstract class AbstractPersistenceIntegrationTest {
	
}
