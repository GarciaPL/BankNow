package pl.garciapl.banknow.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.garciapl.banknow.controller.form.DepositForm;
import pl.garciapl.banknow.controller.form.TransferForm;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.service.AccountService;
import pl.garciapl.banknow.service.TransactionService;
import pl.garciapl.banknow.service.exceptions.GenericBankNowException;
import pl.garciapl.banknow.service.exceptions.InsufficientFundsException;

/**
 * OperationsController - responsible for perform deposit and transfer operations
 *
 * @author lukasz
 */
@Controller
public class OperationsController {

    /**
     * AccountService - responsible for performing actions on account
     */
    @Autowired
    private AccountService accountService;

    /**
     * TransactionService - responsible for performing actions related with transaction
     */
    @Autowired
    private TransactionService transactionService;

    /**
     * Returns view for performing deposit operations
     *
     * @param model Model
     * @return Deposit view
     */

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String getDeposit(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "deposit";
    }

    /**
     * Performs deposit of funds on account
     *
     * @param depositForm DepositForm
     * @param result BindingResult
     * @param model Model
     * @return Model with appropriate message and accounts
     */

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String makeDeposit(DepositForm depositForm, BindingResult result, Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        if (result.hasErrors()) {
            model.addAttribute("message", "Wrong data provided");
            model.addAttribute("accounts", accounts);
            return null;
        } else if (depositForm.getRecipient() == null || depositForm.getAmount() == null) {
            model.addAttribute("message", "Empty account or amount");
            model.addAttribute("accounts", accounts);
            return null;
        } else if (depositForm.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            model.addAttribute("message", "Please provide appropriate amount of founds");
            model.addAttribute("accounts", accounts);
            return null;
        } else {
            transactionService.makeDeposit(depositForm.getRecipient(), depositForm.getAmount());
            model.addAttribute("message", "Deposit successfully executed");
            model.addAttribute("accounts", accounts);
            return "redirect:deposit";
        }
    }

    /**
     * Returns view for performing transfer operations
     *
     * @param model Model
     * @return Transfer view
     */

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String getTransfer(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "transfer";
    }

    /**
     * Performs transfer of funds from one account to another account
     *
     * @param transferForm TransferForm
     * @param result BindingResult
     * @param model Model
     * @return Model with appropriate message and accounts
     */

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
        } else if (transferForm.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            model.addAttribute("message", "Please provide appropriate amount of founds");
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
