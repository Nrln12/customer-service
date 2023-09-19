package com.azercell.customerservice.utils;

import lombok.Data;

@Data
public class ValidationUtils {
    public static Boolean isValidName(String name) {
        return name.length() >= 6;
    }
    public static Boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }
    public static Boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\\\+[0-9]{1,3}[- ]?)?([0-9]{10,11})$";
        return phoneNumber.matches(regex);
    }
    public static Boolean isValidPurchase(Double balance, Double amount){
       return balance>=amount;
    }
}
