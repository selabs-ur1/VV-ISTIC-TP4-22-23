package fr.istic.vv;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RomanNumeraUtils {
        private static final List<Character> triplableCharacters = new ArrayList<>(Arrays.asList('M', 'C', 'X', 'I'));
        private static final List<Character> nonRepeatableCharacters = new ArrayList<>(Arrays.asList('D', 'L', 'V'));
        private static final Map<Character, Integer> romanValues = Map.of(
                'M', 1000,
                'D', 500,
                'C', 100,
                'L', 50,
                'X', 10,
                'V', 5,
                'I', 1
        );

    
        public static boolean isValidRomanNumeral(String value) {
                for (char c : value.toCharArray()) {
                        boolean invalidCharFound = !romanValues.containsKey(c);
                        if (invalidCharFound) {
                                return false;
                        }
                }

                for (char c : triplableCharacters) {
                        String quadCs = "" + c + c + c + c;
                        boolean quadFound = value.contains(quadCs);

                        if (quadFound) {
                                return false;
                        }
                }

                for (char c : nonRepeatableCharacters) {
                        String doubleCs = "" + c + c;
                        boolean doubleFound = value.contains(doubleCs);

                        if (doubleFound) {
                                return false;
                        }
                }

                return true;
        }


        public static int parseRomanNumeral(String numeral) {
                List<Integer> numbers = new ArrayList<>();
                char[] inputs = numeral.toCharArray();

                for (Character c : inputs) {
                        numbers.add(romanValues.get(c));
                }

                List<Integer> sameNumbers1 = new ArrayList<>();
                List<Integer> sameNumbers2 = new ArrayList<>();
                Integer result = 0;
                Collections.reverse(numbers);

                for (Integer input : numbers) {
                        result = oneCycle(sameNumbers1, sameNumbers2, result, input);
                }
                result = oneCycle(sameNumbers1, sameNumbers2, result, 0);

                return oneCycle(sameNumbers1, sameNumbers2, result, 0);
        }

        private static Integer oneCycle(List<Integer> sameNumbers1, List<Integer> sameNumbers2, Integer result, Integer input) {
                if (sameNumbers2.size() < 1) {
                        if (sameNumbers1.size() > 0) {
                                // fill sameNumbers1 or prepare next operation
                                final int values1 = sameNumbers1.get(0);

                                if (input == values1) {
                                        sameNumbers1.add(input);
                                        return result;
                                }
                                // else act on sameNumbers2
                        } else {
                                sameNumbers1.add(input);
                                return result;
                        }
                }

                // acting on sameNumbers2
                if (sameNumbers2.size() == 0) {
                        sameNumbers2.add(input);
                } else {
                        final int values2 = sameNumbers2.get(0);

                        if (input == values2) {
                                sameNumbers2.add(input);
                        } else {
                                // do operation
                                final int values1 = sameNumbers1.get(0);
                                final int sum1 = sameNumbers1.stream().mapToInt(Integer::intValue).sum();
                                final int sum2 = sameNumbers2.stream().mapToInt(Integer::intValue).sum();

                                if (values2 > values1) {
                                        result = result + sum2 + sum1;
                                } else if (values2 < values1) {
                                        if (!(sum2 * 5 == sum1 || sum2 * 10 == sum1)) {
                                                throw new ArithmeticException("The subtracted symbol must be one fifth or one tenth of the larger.");
                                        }
                                        result = result + sum1 - sum2;
                                }

                                // reset lists, then add next input
                                sameNumbers1.clear();
                                sameNumbers2.clear();
                                sameNumbers1.add(input);
                        }
                }
                return result;
        }

        public static String toRomanNumeral(int number) {
                StringBuilder result = new StringBuilder();
                int remainder = number;

                while (remainder >= 1000) {
                        result.append("M");
                        remainder -= 1000;
                }

                // remainder < 1000
                if (remainder >= 900) {
                        result.insert(0, "CM");
                        remainder -= 900;
                }

                // remainder < 900
                if (remainder >= 500) {
                        result.append("D");
                        remainder -= 500;
                }

                // remainder < 450
                if (remainder >= 450) {
                        result.append("LD");
                        remainder -= 450;
                }

                // remainder < 400
                if (remainder >= 400) {
                        result.append("CD");
                        remainder -= 400;
                }

                // remainder < 400
                while (remainder >= 100) {
                        result.append("C");
                        remainder -= 100;
                }

                // remainder < 100
                if (remainder >= 90) {
                        result.append("XC");
                        remainder -= 90;
                }

                // remainder < 90
                if (remainder >= 50) {
                        result.append("L");
                        remainder -= 50;
                }

                // remainder < 50
                if (remainder >= 45) {
                        result.append("VL");
                        remainder -= 45;
                }

                // remainder < 45
                if (remainder >= 40) {
                        result.append("XL");
                        remainder -= 40;
                }

                // remainder < 40
                while (remainder >= 10) {
                        result.append("X");
                        remainder -= 10;
                }

                // remainder < 10
                if (remainder == 9) {
                        result.append("IX");
                        remainder -= 9;
                }

                // remainder < 9
                if (remainder >= 5) {
                        result.append("V");
                        remainder -= 5;
                }

                // remainder < 5
                if (remainder == 4) {
                        result.append("IV");
                        remainder -= 4;
                }

                // remainder < 4
                while (remainder > 0) {
                        result.append("I");
                        remainder--;
                }

                return result.toString();
        }
    
}
