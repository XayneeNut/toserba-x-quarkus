package org.gusanta.toserba.core.util;


import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

public class CheckUtil {

    private CheckUtil(){}

    private static final String INDO_PHONE_REGEX = "^\\+\\d{1,3}\\d{1,14}$";

    public static boolean isStringBlank(String source) {
        return source == null || source.isBlank();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(INDO_PHONE_REGEX);
        return pattern.matcher(phoneNumber).matches();
    }

    public static boolean isDateFormatValid(String date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(FormatUtil.DATE_FORMAT)
                .withResolverStyle(ResolverStyle.SMART);
        try {
            dateFormat.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
