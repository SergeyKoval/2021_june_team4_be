package com.discount.Exception;

import java.text.MessageFormat;

public class PersonSuchNameNotFoundException {
    public PersonSuchNameNotFoundException(String name){
        MessageFormat.format("Could not found person with name:{0}", name);
    }
}