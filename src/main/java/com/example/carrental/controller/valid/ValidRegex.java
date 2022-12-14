package com.example.carrental.controller.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidRegex {

    static public boolean validRegex(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
