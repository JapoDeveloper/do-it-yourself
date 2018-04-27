package co.japo.doityourself.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Slf4j
@Controller
public class IndexController {

    @RequestMapping({"","/","/index"})
    public String index() {
        log.info("Index endpoint of IndexController class was invoked.");
        return "index";
    }
}
