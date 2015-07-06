package pl.garciapl.banknow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.garciapl.banknow.service.TransactionService;

/**
 * TransactionsController - responsible for providing transactions information
 * @author lukasz
 */
@Controller
public class TransactionsController {

    /**
     * TransactionService - responsible for performing actions related with transaction
     */
    @Autowired
    private TransactionService transactionService;

    /**
     * Returns view for performing deposit operations
     * @param model Model
     * @return Transaction view
     */

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String transactions(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "transactions";
    }
}
