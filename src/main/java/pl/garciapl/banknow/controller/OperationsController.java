package pl.garciapl.banknow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.garciapl.banknow.controller.domain.DepositForm;
import pl.garciapl.banknow.controller.domain.TransferForm;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.service.AccountService;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by lukasz on 04.07.15.
 */
@Controller
public class OperationsController {

    @Autowired
    private AccountService accountService;

    private final Logger logger = Logger.getLogger(getClass().getName());

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String getDeposit(Model model) {

        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);

        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String makeDeposit(DepositForm depositForm, BindingResult result, Model model) {

        logger.info(depositForm.toString());

        if (result.hasErrors()) {
            model.addAttribute("message", "Wrong data provided");
            return null;
        } else {
            if (depositForm.getAccount() == null || depositForm.getAmount() == null) {
                model.addAttribute("message", "Empty account or amount");
                return null;
            } else {
                model.addAttribute("message", "Deposit successfully executed");
                return "deposit";
            }
        }
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String getTransfer(Model model) {

        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);

        return "transfer";
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public String makeTransfer(TransferForm transferForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("message", "Wrong data provided");
            return null;
        } else {
            if (transferForm.getSender() == null || transferForm.getRecipient() == null || transferForm.getAmount() == null) {
                model.addAttribute("message", "Empty sender, recipient or amount");
                return null;
            } else {
                model.addAttribute("message", "Transfer successfully executed");
                return "transfer";
            }
        }
    }
}
