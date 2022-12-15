package ua.foxminded.longdivision.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DivisionValidatorImplTest {

    private final DivisionValidator divisionValidator = new DivisionValidatorImpl();

    @Test
    void validate_shouldThrowIllegalArgumentExceptionWhenDivisorIsZero() {
        Exception exception =assertThrows(
            IllegalArgumentException.class, () -> divisionValidator.validate(123, 0));

        String expectedMessage = "/ by zero";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void validate_shouldThrowIllegalArgumentExceptionWhenDividendAndOrDivisorAreLessThenZero() {
        Exception exception =assertThrows(
            IllegalArgumentException.class, () -> divisionValidator.validate(123, -123));

        String expectedMessage = "dividend < 0 and/or divisor < 0";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void validate_shouldNotThrowExceptionWhenArgumentsAreCorrect() {
        assertDoesNotThrow(() -> divisionValidator.validate(1234, 12));
    }
}
