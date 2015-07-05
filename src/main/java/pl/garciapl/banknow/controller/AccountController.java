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

import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    private List<CurrencyUnit> currencyUnits;

    private AccountController() {
        this.currencyUnits = CurrencyUnit.registeredCurrencies();
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getAccount(Model model) {
        model.addAttribute("currencies", currencyUnits);
        return "account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String createAccount(AccountForm accountForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Wrong data provided");
            model.addAttribute("currencies", currencyUnits);
            return null;
        } else {
            accountService.createAccount(new Account(accountForm.getName(), accountForm.getSurname(), accountForm.getAddress(),
                    accountForm.getPhone(), accountForm.getIban(), accountForm.getBalance(), accountForm.getCurrency()));
            model.addAttribute("message", "Account successfully created");
            model.addAttribute("currencies", currencyUnits);
            return "account";
        }
    }
}
