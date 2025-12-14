package com.groupX.ems.utils;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isValidEmail(String email) {
        // TODO: Add robust email validation.
        return email != null && email.contains("@");
    }

    public static boolean isValidPhone(String phone) {
        // TODO: Add robust phone validation.
        return phone != null && phone.length() >= 8;
    }
}

