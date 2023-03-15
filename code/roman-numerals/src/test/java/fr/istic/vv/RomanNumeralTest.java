package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.CharsList;
import net.jqwik.api.constraints.IntRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RomanNumeralTest {
    private static final List<Character> allowedCharacters = new ArrayList<>(Arrays.asList('M', 'D', 'C', 'L','X', 'V', 'I'));
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


    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean checkCharactersOfRomanNumeral(@ForAll("invalidChars") char invalidChar) {
        String invalidInput = "M" + invalidChar + "M";
        boolean invalidCharFound = !RomanNumeraUtils.isValidRomanNumeral(invalidInput);
        return invalidCharFound;
    }

    @Property
    boolean checkTripledCharactersOfRomanNumeral(@ForAll("quadChars") String quadChar) {
        boolean overTripleFound = !RomanNumeraUtils.isValidRomanNumeral(quadChar);
        return overTripleFound;
    }

    @Property
    boolean checkUnrepeatableCharactersOfRomanNumeral(@ForAll("repeatOfUnrepeatableChars") String repeatedChar) {
        boolean unrepeatableFound = !RomanNumeraUtils.isValidRomanNumeral(repeatedChar);
        return unrepeatableFound;
    }

    @Property
    boolean checkLeftHighRightLow(@ForAll("leftHighRightLow") String highLow) {
        return RomanNumeraUtils.parseRomanNumeral(highLow) > romanValues.get(highLow.charAt(0));
    }

    @Property
    boolean checkLeftLowRightHigh(@ForAll("leftLowRightHigh") String lowHigh) {
        return RomanNumeraUtils.parseRomanNumeral(lowHigh) < romanValues.get(lowHigh.charAt(1));
    }

    @Provide
    Arbitrary<Character> invalidChars() {
        return Arbitraries.chars().filter(c -> !allowedCharacters.contains(c));
    }

    @Provide
    Arbitrary<String> quadChars() {
        return Arbitraries.of(triplableCharacters).map(c -> "" + c + c + c + c);
    }

    @Provide
    Arbitrary<String> repeatOfUnrepeatableChars() {
        return Arbitraries.of(nonRepeatableCharacters).map(c -> "" + c + c);
    }

    @Provide
    Arbitrary<String> leftHighRightLow() {
        List<Character> lows = new ArrayList<>(allowedCharacters);
        lows.remove(0);

        Character low = Arbitraries.of(lows).sample();
        List<Character> highChars = allowedCharacters.subList(0, allowedCharacters.indexOf(low));
        return Arbitraries.of(highChars).map(high -> "" + high + low);
    }

    @Provide
    Arbitrary<String> leftLowRightHigh() {
        List<Character> highs = new ArrayList<>(allowedCharacters);
        highs.remove(highs.size() - 1);

        Character high = Arbitraries.of(highs).sample();
        List<Character> lowChars = allowedCharacters.subList(
                allowedCharacters.indexOf(high) + 1, allowedCharacters.size() - 1);
        return Arbitraries.of(lowChars).map(low -> "" + low + high);
    }

}
