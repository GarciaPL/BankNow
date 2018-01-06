package pl.garciapl.banknow.controller;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.garciapl.banknow.model.Transaction;
import pl.garciapl.banknow.model.TransactionType;
import pl.garciapl.banknow.service.TransactionService;

/**
 * Created by lukasz on 05.07.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:banknow-ctx.xml", "classpath:banknow-db-ctx.xml", "classpath:dispatcher-servlet-test.xml"})
public class TransactionsControllerTest {

    private MockMvc mockMvc;
    private InternalResourceViewResolver viewResolver;

    @InjectMocks
    private TransactionsController transactionsController;

    @Mock
    private TransactionService transactionService;

    private Transaction transaction;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        transaction = new Transaction(new BigInteger("123"), new BigInteger("234"), new BigDecimal(25), TransactionType.DEPOSIT);

        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        when(transactionService.getAllTransactions()).thenReturn(transactions);

        viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(transactionsController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void getTransactionsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("transactions")).andReturn();
    }
}
