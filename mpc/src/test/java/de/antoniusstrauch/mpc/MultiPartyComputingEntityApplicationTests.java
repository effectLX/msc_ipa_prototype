//package de.antoniusstrauch.mpc;
//
//import de.antoniusstrauch.mpc.controller.ExampleController;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.core.StringContains.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(ExampleController.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//class MultiPartyComputingEntityApplicationTests {
//
//	@Autowired
//	private MockMvc mock;
//
//	@Test
//	public void shouldReturnExampleEndpoint() throws Exception {
//		this.mock.perform(get("/info"))
//						.andDo(print())
//						.andExpect(status().isOk())
//						.andExpect(content()
//						.string(containsString("This is an example endpoint.")));
//	}
//}
