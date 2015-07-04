package pl.garciapl.banknow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.garciapl.banknow.service.TransactionService;

/**
 * Created by lukasz on 04.07.15.
 */
@Controller
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String transactions(Model model) {

        model.addAttribute("transactions", transactionService.getAllTransactions());

        return "transactions";
    }
}
