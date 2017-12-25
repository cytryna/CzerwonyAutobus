package com.diligentia.czerwony.controller;

import com.diligentia.czerwony.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller {

    @Autowired
    private SystemRepository articleRepository;

    public Controller() {
     }

    // constructor injection also works
    // code here to use the injected articleRepository

}