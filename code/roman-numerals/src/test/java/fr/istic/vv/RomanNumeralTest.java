package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.CharRangeList;
import net.jqwik.api.constraints.Chars;
import net.jqwik.api.constraints.CharsList;
import net.jqwik.engine.properties.Range;
import org.assertj.core.api.Assertions;

public class RomanNumeralTest {

    @Property
    boolean leftIsomorphism(@ForAll int anInt) {
        Assume.that(Range.of(1, 3999).includes(anInt));
        return RomanNumeraUtils.parseRomanNumeral(RomanNumeraUtils.toRomanNumeral(anInt)) == anInt;
    }

    @Property
    void RightIsomorphism(@ForAll @Chars(value = {'M', 'D', 'C', 'X', 'V', 'I', 'L'}) String someText) {
        Assume.that(someText.length() > 0);
        Assume.that(someText.length() < 10);
        if (RomanNumeraUtils.isValidRomanNumeral(someText)) {
            Assertions.assertThat(RomanNumeraUtils.toRomanNumeral(RomanNumeraUtils.parseRomanNumeral(someText))).isEqualTo(someText);
        }
    }
}
