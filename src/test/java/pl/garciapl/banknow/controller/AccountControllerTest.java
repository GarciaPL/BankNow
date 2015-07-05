package pl.garciapl.banknow.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by lukasz on 05.07.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:banknow-ctx.xml", "classpath:banknow-db-ctx.xml", "classpath:dispatcher-servlet-test.xml"})
public class AccountControllerTest {

    private MockMvc mockMvc;
    private InternalResourceViewResolver viewResolver;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {

        AccountController accountController = new AccountController();

        viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(accountController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void accountControllerGetTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("account")).andReturn();
    }

    @Test
    public void accountControllerPostTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/account")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("account")).andReturn();
    }
}
