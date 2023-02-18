package br.com.cristal.util;

public class StringUtils {
    public static boolean onlyNumbers(String field) {
        return field.matches("\\d+");
    }
}
