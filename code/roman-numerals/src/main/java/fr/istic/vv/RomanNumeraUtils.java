package fr.istic.vv;

public class RomanNumeraUtils {

        private static final Map<Character, Integer> ROMAN_NUMERAL_VALUES = new HashMap<Character, Integer>() {{
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
        }};

        /**
         * Verifie si les values sont bien dans la liste pré-définie
         * @param value
         * @return
         */
        public static boolean isValidRomanNumeral(String value) {
                if (value == null || value.isEmpty()) {
                        return false;
                }
                for (char c : value.toCharArray()) {
                        if (!ROMAN_NUMERAL_VALUES.containsKey(c)) {
                                return false;
                        }
                }
                return true;
        }

        /**
         * Calcul la suite de caractere. Vérifie avant s'il est valide. Et fait le calcul. S'il est situé avant un nombre avant alors on soustrait et inversement
         * @param numeral
         * @return
         */
        public static int parseRomanNumeral(String numeral) {
                if (!isValidRomanNumeral(numeral)) {
                        throw new IllegalArgumentException("Invalid Roman numeral: " + numeral);
                }
                int result = 0;
                int lastValue = 0;
                for (int i = numeral.length() - 1; i >= 0; i--) {
                        int currentValue = ROMAN_NUMERAL_VALUES.get(numeral.charAt(i));
                        if (currentValue >= lastValue) {
                                result += currentValue;
                        } else {
                                result -= currentValue;
                        }
                        lastValue = currentValue;
                }
                return result;
        }

        /**
         *
         * @param number
         * @return
         */
        public static String toRomanNumeral(int number) {
                if (number < 1 || number > 3999) {
                        throw new IllegalArgumentException("Number out of range: " + number);
                }
                StringBuilder result = new StringBuilder();
                int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
                String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
                for (int i = 0; i < values.length; i++) {
                        while (number >= values[i]) {
                                result.append(symbols[i]);
                                number -= values[i];
                        }
                }
                return result.toString();
        }
    
}
