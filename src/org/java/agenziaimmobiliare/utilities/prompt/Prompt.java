package org.java.agenziaimmobiliare.utilities.prompt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;

@SuppressWarnings("unused")
public final class Prompt {

    /* Fields */
    private static final Scanner scanner = new Scanner(System.in);
    private static String errorMessage;

    /* Interfaces */
    private static interface InputParser<T> {
        T parse(String input) throws NumberFormatException;
    }

    /* Constructors */
    private Prompt() {
    }

    ;

    /* Getters */
    public static String getErrorMessage() {
        return errorMessage;
    }

    /* Setters */
    public static void setErrorMessage(String errorMessage) {
        Prompt.errorMessage = errorMessage;
    }

    public static void setLocale(Locale locale) {
        scanner.useLocale(locale);
    }

    /* Methods */
    public static String ask(String prompt) {
        return askWithValidation(prompt, String::valueOf);
    }

    public static int askInt(String prompt) {
        return askWithValidation(prompt, Integer::parseInt);
    }

    public static double askDouble(String prompt) {
        return askWithValidation(prompt, Double::parseDouble);
    }

    public static float askFloat(String prompt) {
        return askWithValidation(prompt, Float::parseFloat);
    }

    public static boolean askBool(String prompt) {
        return askWithValidation(prompt, Boolean::parseBoolean);
    }

    public static BigDecimal askBigDecimal(String prompt) {
        return askWithValidation(prompt, BigDecimal::new);
    }

    public static BigInteger askBigInteger(String prompt) {
        return askWithValidation(prompt, BigInteger::new);
    }

    public static long askLong(String prompt) {
        return askWithValidation(prompt, Long::parseLong);
    }

    public static byte askByte(String prompt) {
        return askWithValidation(prompt, Byte::parseByte);
    }

    public static short askShort(String prompt) {
        return askWithValidation(prompt, Short::parseShort);
    }

    private static <T> T askWithValidation(String prompt, InputParser<T> parser) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                return parser.parse(input);
            } catch (NumberFormatException e) {
                System.out.print(errorMessage);
            }

            scanner.nextLine();
        }
    }
}
