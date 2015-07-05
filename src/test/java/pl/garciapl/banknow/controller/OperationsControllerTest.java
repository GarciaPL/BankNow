package pl.garciapl.banknow.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by lukasz on 05.07.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:banknow-ctx.xml", "classpath:banknow-db-ctx.xml"})
public class OperationsControllerTest {

    private MockMvc mockMvc;
    private InternalResourceViewResolver viewResolver;

    @Before
    public void setup() {

        OperationsController operationsController = new OperationsController();

        viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(operationsController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void operationsControllerGetDepositTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deposit")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("deposit")).andReturn();
    }

    @Test
    public void operationsControllerGetTransferTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transfer")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("transfer")).andReturn();
    }
}
