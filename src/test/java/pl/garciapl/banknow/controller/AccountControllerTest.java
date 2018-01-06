package pl.garciapl.banknow.controller;

import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.garciapl.banknow.controller.form.AccountForm;
import pl.garciapl.banknow.dao.AccountDAO;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.service.AccountService;
import pl.garciapl.banknow.service.exceptions.AccountExistsException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:banknow-ctx.xml", "classpath:banknow-db-ctx.xml", "classpath:dispatcher-servlet-test.xml"})
public class AccountControllerTest {

    private MockMvc mockMvc;
    private InternalResourceViewResolver viewResolver;

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    private AccountForm accountForm;

    private Account account;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        account = new Account("john", "malkovich", "Ireland", new BigInteger("500600700"), new BigInteger("1234567890"), new BigDecimal(25),
                "EUR");

        accountForm = new AccountForm();
        accountForm.setName("john");
        accountForm.setSurname("malkovich");
        accountForm.setAddress("Ireland");
        accountForm.setPhone(new BigInteger("500600700"));
        accountForm.setIban(new BigInteger("1234567890"));
        accountForm.setBalance(new BigDecimal(25));
        accountForm.setCurrency("EUR");

        viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(accountController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void getAccountTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account")).andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("account"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("currencies")).andReturn();
    }

    @Test
    public void postAccountTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/account").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", accountForm.getName())
                .param("surname", accountForm.getSurname())
                .param("address", accountForm.getAddress())
                .param("phone", accountForm.getPhone().toString())
                .param("iban", accountForm.getIban().toString())
                .param("balance", accountForm.getBalance().toString())
                .param("currency", accountForm.getCurrency())).andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("account")).
                andExpect(MockMvcResultMatchers.model().attributeExists("currencies")).andReturn();
    }

    @Test
    public void postBindingResultsErrorTest() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);
        String response = accountController.createAccount(accountForm, bindingResult, model);

        assertNull(response);
    }

    @Test
    public void postAccountExistsErrorTest() throws Exception {
        doThrow(new AccountExistsException("Account already exists with iban : " + account.getIban())).when(accountService)
                .createAccount(
                        new Account(accountForm.getName(), accountForm.getSurname(), accountForm.getAddress(),
                                accountForm.getPhone(), accountForm.getIban(), accountForm.getBalance(), accountForm.getCurrency()));

        accountController.createAccount(accountForm, bindingResult, model);
    }
}
