/**
 * 
 */
package bank;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Fabian Krüger
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Rollback(true)
public abstract class AbstractPersistenceIntegrationTest {
	
}
