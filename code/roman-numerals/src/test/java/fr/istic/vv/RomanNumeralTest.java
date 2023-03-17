package fr.istic.vv;

import fr.istic.vv.RomanNumeralUtils;
import net.jqwik.api.*;
import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralTest {

    @Property
    void testParseRomanNumeral(@ForAll("validRomanNumerals") String numeral, @ForAll("validRomanNumeralValues") int value) {
        assertEquals(value, RomanNumeralUtils.parseRomanNumeral(numeral));
    }

    @Property
    void testToRomanNumeral(@ForAll("validRomanNumeralValues") int value, @ForAll("validRomanNumerals") String numeral) {
        assertEquals(numeral, RomanNumeralUtils.toRomanNumeral(value));
    }

    @Provide
    Arbitrary<String> validRomanNumerals() {
        return Arbitraries.strings().withCharRange('I', 'M').ofMaxLength(15).filter(RomanNumeralUtils::isValidRomanNumeral);
    }

    @Provide
    Arbitrary<Integer> validRomanNumeralValues() {
        return Arbitraries.integers().between(1, 3999);
    }

}