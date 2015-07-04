package pl.garciapl.banknow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lukasz on 04.07.15.
 */
@Controller
public class OperationsController {

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit() {
        return "deposit";
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String transfer() {
        return "transfer";
    }

}
