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
public class TransactionsControllerTest {

    private MockMvc mockMvc;
    private InternalResourceViewResolver viewResolver;

    @Before
    public void setup() {

        TransactionsController transactionsController = new TransactionsController();

        viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(transactionsController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void transactionsControllerGetTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("transactions")).andReturn();
    }
}
