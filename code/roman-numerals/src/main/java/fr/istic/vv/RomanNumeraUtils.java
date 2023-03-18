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

    private static char[] chars = { 'M', 'D', 'C', 'L', 'X', 'V', 'I' };

    private static boolean isLower(char of, char by) {
        int containsFirst = -1;
        int containsSecond = -1;
        for (var i = 0; i < chars.length; i++) {
            if (chars[i] == of) containsFirst = i;
            if (chars[i] == by) containsSecond = i;
        }
        if (containsFirst != -1 && containsSecond != -1) {
            return containsFirst < containsSecond;
        } else {
            throw new IllegalStateException("Illegal char detected");
        }
    }

    public static boolean isValidRomanNumeral(String value) {
        char sinceLast = ' ';
        int sinceLastCount = 0;
        for (var i = 0; i < value.length(); i++) {
            if ("MDCLXVI".contains(""+value.charAt(i))) {
                // Repeating roles
                if (value.charAt(i) != sinceLast) {
                    sinceLast = value.charAt(i);
                    sinceLastCount = 1;
                } else {
                    if ("DLV".contains("" + sinceLast)) {
                        return false;
                    }
                    sinceLastCount ++;
                }
                if (sinceLastCount > 3) {
                    return false;
                }
                // Substraction roles
                {
                    // Skip if first
                    if (i == 0) {
                        continue;
                    }

                    var lastCh = value.charAt(i - 1);
                    var curr = value.charAt(i);

                    if (isLower(lastCh, curr)) {
                        if ("CXV".contains("" + lastCh)) {
                            continue;
                        } else {
                            return false;
                        }
                    }
                }
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
                result += (next - current);
                i ++;
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
        if (number < 0 || number > 3999) {
            throw new IllegalStateException("Out of bound!");
        }
        var pos3 = tpos3[number / 1000];
        number %= 1000;
        var pos2 = tpos2[number / 100];
        number %= 100;
        var pos1 = tpos1[number / 10];
        number %= 10;
        var pos0 = tpos0[number % 10];

        return "" + pos3 + pos2 + pos1 + pos0;
    }

    public static void main(String[] args) {
        System.out.println(RomanNumeraUtils.isValidRomanNumeral("M"));
        System.out.println(RomanNumeraUtils.parseRomanNumeral("M"));
        System.out.println(RomanNumeraUtils.toRomanNumeral(1000));

        System.out.println(RomanNumeraUtils.toRomanNumeral(RomanNumeraUtils.parseRomanNumeral("M")));
    }
}