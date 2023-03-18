package fr.istic.vv;

import net.jqwik.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RomanNumeralTest {

    /**
     * This test is nor doable because the coding this test would be the same that the function
     * @param input
     * @return
     */
    /*
    @Property
    boolean testIsValidRomanNumeral(@ForAll String input) {
        return RomanNumeraUtils.isValidRomanNumeral(input);
    }
    */

    @Property
    boolean testParseRomanNumeral(@ForAll int input) {
        Assume.that(input >= 1 && input <= 3999); // add this assumption to limit the input range
        return input == RomanNumeraUtils.parseRomanNumeral(RomanNumeraUtils.toRomanNumeral(input));
    }

    @Property
    boolean testToRomanNumeral(@ForAll int input) {
        Assume.that(input >= 1 && input <= 3999); // add this assumption to limit the input range
        String romanNumeral = RomanNumeraUtils.toRomanNumeral(input);
        return input == RomanNumeraUtils.parseRomanNumeral(romanNumeral) && RomanNumeraUtils.isValidRomanNumeral(romanNumeral);
    }
}
