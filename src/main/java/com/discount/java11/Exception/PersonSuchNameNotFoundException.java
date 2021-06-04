package com.discount.java11.Exception;

import java.text.MessageFormat;

public class PersonSuchNameNotFoundException {
    public PersonSuchNameNotFoundException(String name){
        MessageFormat.format("Could not found person with iname:{0}", name);
    }
}