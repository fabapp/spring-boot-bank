/**
 * 
 */
package bank;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Fabian Krüger
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public abstract class AbstractServiceIntegrationTest {
	
}
