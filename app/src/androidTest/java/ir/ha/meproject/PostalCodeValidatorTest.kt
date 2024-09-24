package ir.ha.meproject

import ir.ha.meproject.common.extensions.isValidPostalCode
import org.junit.Assert.*
import org.junit.Test

class PostalCodeValidatorTest {

    @Test
    fun postalCode_isValid_whenAllRulesAreMet() {
        val validPostalCode = "1234567890"
        assertTrue(isValidPostalCode(validPostalCode))
    }

    @Test
    fun postalCode_isInvalid_whenLengthIsNot10() {
        val invalidPostalCode = "12345"
        assertFalse(isValidPostalCode(invalidPostalCode))
    }

    @Test
    fun postalCode_isInvalid_whenFirstFiveContain0or2() {
        val invalidPostalCode1 = "0234567890"
        val invalidPostalCode2 = "1234067890"

        assertFalse(isValidPostalCode(invalidPostalCode1))
        assertFalse(isValidPostalCode(invalidPostalCode2))
    }

    @Test
    fun postalCode_isInvalid_whenFifthDigitIs5() {
        val invalidPostalCode = "1234557890"
        assertFalse(isValidPostalCode(invalidPostalCode))
    }

    @Test
    fun postalCode_isInvalid_whenSixthDigitIs0() {
        val invalidPostalCode = "1234560789"
        assertFalse(isValidPostalCode(invalidPostalCode))
    }

    @Test
    fun postalCode_isInvalid_whenLastFourContainNonNumeric() {
        val invalidPostalCode = "12345678AB"
        assertFalse(isValidPostalCode(invalidPostalCode))
    }

    @Test
    fun postalCode_isInvalid_whenLastFourAre0000() {
        val invalidPostalCode = "1234560000"
        assertFalse(isValidPostalCode(invalidPostalCode))
    }

    @Test
    fun postalCode_isInvalid_whenAllDigitsAreSame() {
        val invalidPostalCode = "1111111111"
        assertFalse(isValidPostalCode(invalidPostalCode))
    }
}

