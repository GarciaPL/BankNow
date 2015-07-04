package pl.garciapl.banknow.controller;

import org.joda.money.CurrencyUnit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.garciapl.banknow.controller.domain.AccountForm;

import java.util.logging.Logger;

/**
 * Created by lukasz on 04.07.15.
 */
@Controller
public class AccountController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getAccount(Model model) {
        model.addAttribute("currencies", CurrencyUnit.registeredCurrencies());
        return "account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String createAccount(AccountForm accountForm, BindingResult result, Model model) {

        logger.info(accountForm.toString());

        if (result.hasErrors()) {
            model.addAttribute("message", "Wrong data provided");
            model.addAttribute("currencies", CurrencyUnit.registeredCurrencies());
            return null;
        } else {
            model.addAttribute("message", "Account successfully created");
            model.addAttribute("currencies", CurrencyUnit.registeredCurrencies());
            return "account";
        }
    }
}
