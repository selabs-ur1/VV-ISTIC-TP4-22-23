package fr.istic.vv;

import java.util.Map;

public class RomanNumeraUtils {

    private static Map<Character, Integer> symbols = Map.of(
            'M', 1000,
            'D', 500,
            'C', 100,
            'L', 50,
            'X', 10,
            'V', 5,
            'I', 1);

    private static String[] tpos3 = { "", "M", "MM", "MMM" };
    private static String[] tpos2 = { "",  "C",  "CC",  "CCC",  "CD",
            "D", "DC", "DCC", "DCCC", "CM" };
    private static String[] tpos1 = { "",  "X",  "XX",  "XXX",  "XL",
            "L", "LX", "LXX", "LXXX", "XC" };
    private static String[] tpos0 = { "",  "I",  "II",  "III",  "IV",
            "V", "VI", "VII", "VIII", "IX" };

    public static boolean isValidRomanNumeral(String value) {
        for (var i = 0; i < value.length(); i++) {
            if ("MDCLXVI".contains(""+value.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static int parseRomanNumeral(String numeral) {
        var result = 0;
        for (var i = 0; i < numeral.length(); i++) {
            var current = symbols.get(numeral.charAt(i));
            int next;
            if (i + 1 < numeral.length()) {
                next = symbols.get(numeral.charAt(i+1));
            } else {
                next = 0;
            }

            if (current < next) {
                result += next - current;
            } else {
                result += current;
            }
        }
        if (result > 0) {
            return result;
        } else {
            throw new IllegalStateException("Not a valid number");
        }

    }

    public static String toRomanNumeral(int number) {
        var pos3 = tpos3[number / 1000];
        number %= 1000;
        var pos2 = tpos2[number / 100];
        number %= 100;
        var pos1 = tpos1[number / 10];
        number %= 10;
        var pos0 = tpos0[number % 10];

        return "" + pos3 + pos2 + pos1 + pos0;
    }

}