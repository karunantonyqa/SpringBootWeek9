package integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qa.mySpringBootDatabase.MySpringBootDatabaseApplication;
import com.qa.mySpringBootDatabase.model.MySpringBootDataModel;
import com.qa.mySpringBootDatabase.repository.MySpringBootRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootDatabaseApplication.class})
@AutoConfigureMockMvc
public class IntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private MySpringBootRepository myRepo;
	
	@Before
	public void clearDB() {
		myRepo.deleteAll();
	}
	
	//GET Test
	@Test
	public void findNRetrievePFromDB() throws Exception {
		myRepo.save(new MySpringBootDataModel("Ani", "Portugal", 23));
		mvc.perform(get("/api/person")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name", is("Ani")));
	}
	
	//POST Test
	@Test
	public void addPersonToDBTest() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/api/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\" : \"Robert\",\"address\" : \"Atlantis\", \"age\" : 200}"))
		.andExpect(status()
				.isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("Robert")));
	}
	
	@Test
	public void updatePersonDBTest() throws Exception {
		myRepo.save(new MySpringBootDataModel("Ani", "Portugal", 23));
		
		Long id = myRepo.findAll().get(0).getId();
		
		mvc.perform(MockMvcRequestBuilders.put("/api/person/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\" : \"PutName\",\"address\" : \"putAtlantis\", \"age\" : 200}"))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("PutName")));
	}
	
	@Test
	public void deletePersonDBTest() throws Exception {
		myRepo.save(new MySpringBootDataModel("Ani", "Portugal", 23));
		
		Long id = myRepo.findAll().get(0).getId();
		
		mvc.perform(MockMvcRequestBuilders.delete("/api/person/" + id))
		.andExpect(status().isOk());

		
	}

}
