package repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.mySpringBootDatabase.MySpringBootDatabaseApplication;
import com.qa.mySpringBootDatabase.model.MySpringBootDataModel;
import com.qa.mySpringBootDatabase.repository.MySpringBootRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringBootDatabaseApplication.class)
@DataJpaTest
public class RepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private MySpringBootRepository myRepo;
	
	@Test
	public void retrieveByIdTest() {
		MySpringBootDataModel model1 = new MySpringBootDataModel("Bob","Space",49);
		entityManager.persist(model1);
		entityManager.flush();
		assertTrue(myRepo.findById(model1.getId()).isPresent());
	}
	
	@Test
	public void retrieveByNameTest() {
		MySpringBootDataModel model1 = new MySpringBootDataModel("Bob","Space",49);
		entityManager.persist(model1);
		entityManager.flush();
		MySpringBootDataModel result = myRepo.findByName("Bob");
		assertEquals("Bob", result.getName());
	}

}
