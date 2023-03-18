package fr.istic.vv;


import java.util.HashMap;
import java.util.Map;


public class RomanNumeraUtils {
    
        public static boolean isValidRomanNumeral(String value) {
                
                String pattern = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

                return value.matches(pattern);
        }
    
        public static int parseRomanNumeral(String numeral) {
        
                Map<Character, Integer> numeralMap = new HashMap<>();
                numeralMap.put('I', 1);
                numeralMap.put('V', 5);
                numeralMap.put('X', 10);
                numeralMap.put('L', 50);
                numeralMap.put('C', 100);
                numeralMap.put('D', 500);
                numeralMap.put('M', 1000);

                int result = 0;
                int previousValue = 0;

                for (int i = numeral.length() - 1; i >= 0; i--) {
                        int currentValue = numeralMap.get(numeral.charAt(i));
                        if (currentValue < previousValue) {
                        result -= currentValue;
                        } else {
                        result += currentValue;
                        }
                        previousValue = currentValue;
        }

        return result;
        }

    public static String toRomanNumeral(int number) {
        if (number <= 0 || number > 3999) {
            throw new IllegalArgumentException("Number must be between 1 and 3999");
        }

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        StringBuilder sb = new StringBuilder();

        sb.append(thousands[number / 1000])
                .append(hundreds[(number % 1000) / 100])
                .append(tens[(number % 100) / 10])
                .append(ones[number % 10]);

        return sb.toString();
    }


}
