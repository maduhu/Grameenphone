package com.cc.grameenphone.utils;

import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseIntArray;

/**
 * Created by aditlal on 20/09/15.
 */
public class PhoneUtils {

    public static String normalizeNum(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int len = phoneNumber.length();
        for (int i = 0; i < len; i++) {
            char c = phoneNumber.charAt(i);
            // Character.digit() supports ASCII and Unicode digits (fullwidth, Arabic-Indic, etc.)
            int digit = Character.digit(c, 10);
            if (digit != -1) {
                sb.append(digit);
            } else if (sb.length() == 0 && c == '+') {
                sb.append(c);
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                return normalizeNum(convertKeypadLettersToDigits(phoneNumber));
            }
        }


        return sb.toString();
    }

    public static String convertKeypadLettersToDigits(String input) {
        if (input == null) {
            return input;
        }
        int len = input.length();
        if (len == 0) {
            return input;
        }

        char[] out = input.toCharArray();

        for (int i = 0; i < len; i++) {
            char c = out[i];
            // If this char isn't in KEYPAD_MAP at all, just leave it alone.
            out[i] = (char) KEYPAD_MAP.get(c, c);
        }

        return new String(out);
    }

    public static Uri ussdToCallableUri(String ussd) {

        String uriString = "";

        if (!ussd.startsWith("tel:"))
            uriString += "tel:";

        for (char c : ussd.toCharArray()) {

            if (c == '#')
                uriString += Uri.encode("#");
            else
                uriString += c;
        }

        return Uri.parse(uriString);
    }

    private static final SparseIntArray KEYPAD_MAP = new SparseIntArray();

    static {
        KEYPAD_MAP.put('a', '2');
        KEYPAD_MAP.put('b', '2');
        KEYPAD_MAP.put('c', '2');
        KEYPAD_MAP.put('A', '2');
        KEYPAD_MAP.put('B', '2');
        KEYPAD_MAP.put('C', '2');

        KEYPAD_MAP.put('d', '3');
        KEYPAD_MAP.put('e', '3');
        KEYPAD_MAP.put('f', '3');
        KEYPAD_MAP.put('D', '3');
        KEYPAD_MAP.put('E', '3');
        KEYPAD_MAP.put('F', '3');

        KEYPAD_MAP.put('g', '4');
        KEYPAD_MAP.put('h', '4');
        KEYPAD_MAP.put('i', '4');
        KEYPAD_MAP.put('G', '4');
        KEYPAD_MAP.put('H', '4');
        KEYPAD_MAP.put('I', '4');

        KEYPAD_MAP.put('j', '5');
        KEYPAD_MAP.put('k', '5');
        KEYPAD_MAP.put('l', '5');
        KEYPAD_MAP.put('J', '5');
        KEYPAD_MAP.put('K', '5');
        KEYPAD_MAP.put('L', '5');

        KEYPAD_MAP.put('m', '6');
        KEYPAD_MAP.put('n', '6');
        KEYPAD_MAP.put('o', '6');
        KEYPAD_MAP.put('M', '6');
        KEYPAD_MAP.put('N', '6');
        KEYPAD_MAP.put('O', '6');

        KEYPAD_MAP.put('p', '7');
        KEYPAD_MAP.put('q', '7');
        KEYPAD_MAP.put('r', '7');
        KEYPAD_MAP.put('s', '7');
        KEYPAD_MAP.put('P', '7');
        KEYPAD_MAP.put('Q', '7');
        KEYPAD_MAP.put('R', '7');
        KEYPAD_MAP.put('S', '7');

        KEYPAD_MAP.put('t', '8');
        KEYPAD_MAP.put('u', '8');
        KEYPAD_MAP.put('v', '8');
        KEYPAD_MAP.put('T', '8');
        KEYPAD_MAP.put('U', '8');
        KEYPAD_MAP.put('V', '8');

        KEYPAD_MAP.put('w', '9');
        KEYPAD_MAP.put('x', '9');
        KEYPAD_MAP.put('y', '9');
        KEYPAD_MAP.put('z', '9');
        KEYPAD_MAP.put('W', '9');
        KEYPAD_MAP.put('X', '9');
        KEYPAD_MAP.put('Y', '9');
        KEYPAD_MAP.put('Z', '9');
    }
}
