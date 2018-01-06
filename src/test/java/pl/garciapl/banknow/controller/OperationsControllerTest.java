package pl.garciapl.banknow.controller;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
import pl.garciapl.banknow.controller.form.DepositForm;
import pl.garciapl.banknow.controller.form.TransferForm;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.service.AccountService;
import pl.garciapl.banknow.service.TransactionService;
import pl.garciapl.banknow.service.exceptions.GenericBankNowException;
import pl.garciapl.banknow.service.exceptions.InsufficientFundsException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:banknow-ctx.xml", "classpath:banknow-db-ctx.xml", "classpath:dispatcher-servlet-test.xml"})
public class OperationsControllerTest {

    private MockMvc mockMvc;
    private InternalResourceViewResolver viewResolver;

    @InjectMocks
    private OperationsController operationsController;

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionService transactionService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    private Account account;

    private DepositForm depositForm;

    private TransferForm transferForm;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        account = new Account("john", "malkovich", "Ireland", new BigInteger("500600700"), new BigInteger("1234567890"), new BigDecimal(25),
                "EUR");

        depositForm = new DepositForm();
        depositForm.setRecipient(new BigInteger("1234567890"));
        depositForm.setAmount(new BigDecimal(100));

        transferForm = new TransferForm();
        transferForm.setSender(new BigInteger("1234567890"));
        transferForm.setRecipient(new BigInteger("2345678901"));
        transferForm.setAmount(new BigDecimal(5));

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        when(accountService.getAllAccounts()).thenReturn(accounts);

        viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(operationsController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void getDepositTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deposit")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("deposit")).andReturn();
    }

    @Test
    public void postDepositTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/deposit").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("recipient", "1234567890")
                .param("amount", "25")).andExpect(MockMvcResultMatchers.redirectedUrl("deposit?message=Deposit+successfully+executed"))
                .andReturn();
    }

    @Test
    public void postDepositBindingResultsErrorTest() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);
        String response = operationsController.makeDeposit(depositForm, bindingResult, model);

        assertNull(response);
    }

    @Test
    public void postDepositRecipientNullTest() throws Exception {
        depositForm.setRecipient(null);
        String response = operationsController.makeDeposit(depositForm, bindingResult, model);

        assertNull(response);
    }

    @Test
    public void postDepositAmountZeroTest() throws Exception {
        depositForm.setAmount(BigDecimal.ZERO);
        String response = operationsController.makeDeposit(depositForm, bindingResult, model);

        assertNull(response);
    }

    @Test
    public void getTransferTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transfer")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("transfer")).andReturn();
    }

    @Test
    public void postTransferTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/transfer").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("sender", "1234567890")
                .param("recipient", "2345678901")
                .param("amount", "5")).andExpect(MockMvcResultMatchers.redirectedUrl("transfer?message=Transfer+successfully+executed"))
                .andReturn();
    }

    @Test
    public void postTransferBindingResultsErrorTest() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);
        String response = operationsController.makeTransfer(transferForm, bindingResult, model);

        assertNull(response);
    }

    @Test
    public void postTransferSenderNullTest() throws Exception {
        transferForm.setSender(null);
        String response = operationsController.makeTransfer(transferForm, bindingResult, model);

        assertNull(response);
    }

    @Test
    public void postTransferRecipientNullTest() throws Exception {
        transferForm.setRecipient(null);
        String response = operationsController.makeTransfer(transferForm, bindingResult, model);

        assertNull(response);
    }

    @Test
    public void postTransferAmountNullTest() throws Exception {
        transferForm.setAmount(null);
        String response = operationsController.makeTransfer(transferForm, bindingResult, model);

        assertNull(response);
    }

    @Test
    public void postTransferAmountZeroTest() throws Exception {
        transferForm.setAmount(BigDecimal.ZERO);
        String response = operationsController.makeTransfer(transferForm, bindingResult, model);

        assertNull(response);
    }

    @Test
    public void postTransferInsufficientFundsExceptionTest() throws Exception {
        doThrow(new InsufficientFundsException("Insufficient funds")).when(transactionService)
                .makeTransfer(transferForm.getSender(), transferForm.getRecipient(), transferForm.getAmount());

        String response = operationsController.makeTransfer(transferForm, bindingResult, model);

        assertNull(response);
    }

    @Test
    public void postTransferGenericBankNowExceptionTest() throws Exception {
        doThrow(new GenericBankNowException("Sender and recipient are the same accounts")).when(transactionService)
                .makeTransfer(transferForm.getSender(), transferForm.getRecipient(), transferForm.getAmount());

        String response = operationsController.makeTransfer(transferForm, bindingResult, model);

        assertNull(response);
    }

}
