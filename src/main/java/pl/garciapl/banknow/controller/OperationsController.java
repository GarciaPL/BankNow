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
import pl.garciapl.banknow.service.TransactionService;
import pl.garciapl.banknow.service.exceptions.GenericBankNowException;
import pl.garciapl.banknow.service.exceptions.InsufficientFundsException;

import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
@Controller
public class OperationsController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String getDeposit(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String makeDeposit(DepositForm depositForm, BindingResult result, Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        if (result.hasErrors()) {
            model.addAttribute("message", "Wrong data provided");
            model.addAttribute("accounts", accounts);
            return null;
        } else {
            if (depositForm.getRecipient() == null || depositForm.getAmount() == null) {
                model.addAttribute("message", "Empty account or amount");
                model.addAttribute("accounts", accounts);
                return null;
            } else {
                transactionService.makeDeposit(depositForm.getRecipient(), depositForm.getAmount());
                model.addAttribute("message", "Deposit successfully executed");
                model.addAttribute("accounts", accounts);
                return "redirect:deposit";
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
        List<Account> accounts = accountService.getAllAccounts();
        if (result.hasErrors()) {
            model.addAttribute("message", "Wrong data provided");
            model.addAttribute("accounts", accounts);
            return null;
        } else if (transferForm.getSender() == null || transferForm.getRecipient() == null || transferForm.getAmount() == null) {
            model.addAttribute("message", "Empty sender, recipient or amount");
            model.addAttribute("accounts", accounts);
            return null;
        } else {
            try {
                transactionService.makeTransfer(transferForm.getSender(), transferForm.getRecipient(), transferForm.getAmount());
            } catch (InsufficientFundsException e) {
                model.addAttribute("message", e.getMessage());
                model.addAttribute("accounts", accounts);
                return null;
            } catch (GenericBankNowException e) {
                model.addAttribute("message", e.getMessage());
                model.addAttribute("accounts", accounts);
                return null;
            }
            model.addAttribute("message", "Transfer successfully executed");
            model.addAttribute("accounts", accounts);
            return "redirect:transfer";
        }
    }
}
