package com.sigvetl.twoDog.controller;

import com.sigvetl.twoDog.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrorController {

    @Autowired
    private ErrorService errorService;

    @PostMapping("error")
    public String error(String error, Model model){
        this.errorService.setErrorMsg(error);
        model.addAttribute("error", this.errorService.getError());
        return "error";
    }
}