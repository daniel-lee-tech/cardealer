package org.danlee.cardealer.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    public static String createPasswordDigest(String plainTextPassword) {
        String passwordDigest = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(plainTextPassword.getBytes());
            passwordDigest = new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return passwordDigest;
    }
}
