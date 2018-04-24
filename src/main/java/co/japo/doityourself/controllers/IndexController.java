package co.japo.doityourself.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
public class IndexController {

    private static final Logger LOG = Logger.getLogger(IndexController.class.getName());

    @RequestMapping({"","/","/index"})
    public String index() {
        LOG.info("Method index() of IndexController class was invoked.");
        return "index";
    }
}
