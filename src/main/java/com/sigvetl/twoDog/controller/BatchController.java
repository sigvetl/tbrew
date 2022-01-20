package com.sigvetl.twoDog.controller;

import com.sigvetl.twoDog.model.BatchForm;
import com.sigvetl.twoDog.model.BeerForm;
import com.sigvetl.twoDog.service.BatchService;
import com.sigvetl.twoDog.service.BeerService;
import com.sigvetl.twoDog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BatchController {
    @Autowired
    BatchService batchService;

    @Autowired
    UserService userService;

    @Autowired
    BeerService beerService;

    @PostMapping("/batches")
    public String addUpdateBatch(Authentication auth, BatchForm batchForm, BeerForm beerForm, Model model){
        if (this.batchService.batchExists(batchForm)){
            this.batchService.updateBatch(batchForm);
        } else{
            this.batchService.trackLoggedInUserId(auth.getName());
            this.batchService.createBatch(batchForm);
        }

        //Update homeController
        HomeController.updateHome(auth, model, beerService, batchService, userService);

        return "home";
    }

    @GetMapping("/batch/delete/{batchid}")
    public String deleteBatch(@PathVariable("batchid") Integer batchId, Authentication auth, BeerForm beerForm, BatchForm batchForm, Model model){
        this.batchService.deleteBatch(batchId);
        HomeController.updateHome(auth, model, this.beerService, this.batchService, this.userService);
        return "home";
    }

}
