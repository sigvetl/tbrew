package com.sigvetl.tBrew.controller;

import com.sigvetl.tBrew.model.BatchForm;
import com.sigvetl.tBrew.model.BeerForm;
import com.sigvetl.tBrew.service.BatchService;
import com.sigvetl.tBrew.service.BeerService;
import com.sigvetl.tBrew.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private BeerService beerService;

    @Autowired
    private BatchService batchService;

    @GetMapping
    public String home(Authentication authentication, Model model, BeerForm beerForm, BatchForm batchForm) {
        String username = authentication.getName();
        this.beerService.trackLoggedInUserId(username);
        this.batchService.trackLoggedInUserId(username);

        updateHome(authentication, model, this.beerService, this.batchService, this.userService);

        return "home";
    }

    static void updateHome(Authentication auth, Model model, BeerService beerService,
                           BatchService batchService, UserService userService){
        model.addAttribute("beers", beerService.getBeers());
        model.addAttribute("batches", batchService.getBatches());
        model.addAttribute("currentid", userService.getUserId(auth.getName()));
    }
}
