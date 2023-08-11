package com.baraq.merchantsystem.utilities;

import java.util.Date;
import java.util.Random;

public class CIFUtils {
    public static String generateAndPadRandomNumber(int max) {
        int lenDigits = (int) (Math.log10(max - 1) + 1);
        Random random = new Random();
        int randomNumber = random.nextInt(max);
        return String.format("%0" + lenDigits + "d", randomNumber);
    }

    public static String timeStamp() {
        Date currentDate = new Date();
        long timeStampSeconds = currentDate.getTime() / 1000;
        String sTimeStamp = String.valueOf(timeStampSeconds);
        String substrTimeStamp = sTimeStamp.substring(0, 10);
        return substrTimeStamp;
    }

    public static String checkdigitCalculator_onedigit(String timestamp) {
        int sum_even_digits = 0;
        int sum_odd_digits = 0;

        // sum of even and odd digits
        for (int i = timestamp.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(timestamp.charAt(i));
            if (i % 2 == 1) {
                sum_odd_digits += digit;
            } else {
                sum_even_digits += digit;
            }
        }

        // generate checkdigit
        int odd_digits_sum_mult = sum_odd_digits * 3;
        int total_sum = odd_digits_sum_mult + sum_even_digits;
        int checkdigit = 10 - (total_sum % 10);

        String sCheckDigit = String.valueOf(checkdigit);
        int checkdigit_len = sCheckDigit.length();

        if (checkdigit_len > 1) {
            checkdigit = 0;
            for (int i = 0; i < sCheckDigit.length(); i++) {
                checkdigit += Character.getNumericValue(sCheckDigit.charAt(i));
            }
        }
        return String.valueOf(checkdigit);
    }
}
