package com.akangcupez.formvalidation.util;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Matcher;

/**
 * @author Aji Subastian (akangcupez@gmail.com)
 */
public class Validator {

    public static boolean hasValue(String s) {
        return (!TextUtils.isEmpty(s));
    }

    public static boolean hasExactLength(String s, int l) {
        return hasValue(s) && s.length() == l;
    }

    public static boolean hasMinLength(String s, int l) {
        return hasValue(s) && s.length() >= l;
    }

    public static boolean hasMaxLength(String s, int l) {
        return hasValue(s) && s.length() <= l;
    }

    public static boolean hasBetweenLength(String s, int l1, int l2) {
        return hasValue(s) && s.length() >= l1 && s.length() <= l2;
    }

    public static boolean isValidEmail(String s) {
        Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(s);
        return matcher.matches();
    }

    public static boolean isValidUrl(String s) {
        Matcher matcher = Patterns.WEB_URL.matcher(s);
        return matcher.matches();
    }

}
