package pl.garciapl.banknow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HomeController - responsible for index view presentation
 * @author lukasz
 */

@Controller
public class HomeController {

    /**
     * Returns string with test message
     * @return Hello BankNow!
     */

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String home() {
        return "Hello BankNow!";
    }

    /**
     * Returns index view
     * @return Index view
     */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "index";
    }

    /**
     * Returns index view
     * @return Index view
     */

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}
