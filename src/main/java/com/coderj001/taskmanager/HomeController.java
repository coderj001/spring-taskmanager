package com.coderj001.taskmanager;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String index(Model model){
        logger.info("Index page requested at: {}", System.currentTimeMillis());
        model.addAttribute("timestamp", System.currentTimeMillis());  // Add this line
        model.addAttribute("message", "Hello from Spring!");
        model.addAttribute("name", "Raju Ghorai");
        return "index";
    }
}
