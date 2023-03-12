package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;


public class RomanNumeralTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    void toRomanNumeralShouldProduceValidRomanNumeral(@ForAll @IntRange(min=0, max=3999) int number) {
        String romanNumeral = RomanNumeraUtils.toRomanNumeral(number);
        assertThat(RomanNumeraUtils.isValidRomanNumeral(romanNumeral)).isTrue();
    }

    @Property
    void parseRomanNumeralShouldInverseToRomanNumeral(@ForAll("romanNumeral") String romanNumeral) {
        int number = RomanNumeraUtils.parseRomanNumeral(romanNumeral);
        assertThat(RomanNumeraUtils.toRomanNumeral(number)).isEqualTo(romanNumeral);
    }

    @Provide
    Arbitrary<String> romanNumeral() {
        return Arbitraries.strings()
                .withCharRange('I', 'I').andChars('V', 'X', 'L', 'C', 'D', 'M')
                .ofMinLength(1).ofMaxLength(15)
                .injectDuplicates(0.2);
    }
}
