package com.postgrado.ecommerce.exception;

public class EmailAlreadyUsed extends RuntimeException{

    private static final String ERROR_MESSAGE = "Email %s is already used";

    public EmailAlreadyUsed (String email){
        super(String.format(ERROR_MESSAGE, email));
    }
}
