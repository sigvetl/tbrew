package com.sigvetl.twoDog.service;

import com.sigvetl.twoDog.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

    @Autowired
    private UserService userService;

    private Error error = new Error();


    public void setErrorMsg(String msg){
        error.setMsg(msg);
    }

    public Error getError(){
        return error;
    }
}