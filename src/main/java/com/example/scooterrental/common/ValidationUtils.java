package com.example.scooterrental.common;

import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Integer MIN_AGE  = 1;
    private static final Integer MAX_AGE = 100;
    private static final Integer MIN_SPEED = 1;
    private static final Integer MAX_SPEED = 40;
    private static Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&â€™*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    public static boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    public static boolean isNull(Object object){
        return object == null;
    }

    public static boolean isIncorrectEmail (String email){
        return !emailPattern.matcher(email).matches();
    }

    public static boolean isIncorrectAge (Integer age){
        return age < MIN_AGE || age > MAX_AGE;
    }

    public static boolean isIncorrectMaxSpeed(Integer maxSpeed){
        return maxSpeed < MIN_SPEED || maxSpeed > MAX_SPEED;
    }

}
