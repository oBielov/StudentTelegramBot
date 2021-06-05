package com.goit.telegrambot;

import org.apache.commons.validator.routines.EmailValidator;

public class UserService {

    public boolean emailCheck(String email){
        return (EmailValidator.getInstance().isValid(email));
    }

}
