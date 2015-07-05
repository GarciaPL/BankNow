package pl.garciapl.banknow.controller;

import org.joda.money.CurrencyUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.garciapl.banknow.controller.domain.AccountForm;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.service.AccountService;
import pl.garciapl.banknow.service.exceptions.AccountExistsException;

import java.util.List;

/**
 * AccountController - responsible for creation of account
 * @author lukasz
 */
@Controller
public class AccountController {

    /**
     * AccountService - responsible for performing actions on account
     */
    @Autowired
    private AccountService accountService;

    /**
     * Currencies list compatible with ISO 4217
     */
    private List<CurrencyUnit> currencyUnits;

    public AccountController() {
        this.currencyUnits = CurrencyUnit.registeredCurrencies();
    }

    /**
     * Returns view with form to creation of account
     * @param model Model
     * @return Account view
     */

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getAccount(Model model) {
        model.addAttribute("currencies", currencyUnits);
        return "account";
    }

    /**
     * Performs registration of new account
     * @param accountForm AccountForm
     * @param result BindingResult
     * @param model Model
     * @return Model with appropriate message and currencies
     */

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String createAccount(AccountForm accountForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Wrong data provided");
            model.addAttribute("currencies", currencyUnits);
            return null;
        } else {
            try {
                accountService.createAccount(new Account(accountForm.getName(), accountForm.getSurname(), accountForm.getAddress(),
                        accountForm.getPhone(), accountForm.getIban(), accountForm.getBalance(), accountForm.getCurrency()));
            } catch (AccountExistsException e) {
                model.addAttribute("message", e.getMessage());
                model.addAttribute("currencies", currencyUnits);
                return "account";
            }
            model.addAttribute("message", "Account successfully created");
            model.addAttribute("currencies", currencyUnits);
            return "account";
        }
    }
}
