package ua.foxminded.longdivision;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.foxminded.longdivision.domain.DivisionResult;
import ua.foxminded.longdivision.domain.DivisionStep;
import ua.foxminded.longdivision.provider.DivisionMathProvider;
import ua.foxminded.longdivision.provider.DivisionViewProvider;
import ua.foxminded.longdivision.validator.DivisionValidator;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class IntegerDivisionCalculatorTest {
    @InjectMocks
    private IntegerDivisionCalculator integerDivisionCalculator;
    @Mock
    private DivisionMathProvider divisionMathProvider;
    @Mock
    private DivisionViewProvider divisionViewProvider;
    @Mock
    private DivisionValidator divisionValidator;

    @Test
    void calculateDivision_shouldReturnCorrectLongDivisionS() {
        int dividend = 1234;
        int divisor = 4;

        String expectedResult = """
            _1234│4
            12  │---
             --  │308
            _34
             32
             --
              2""";

        ArrayList<DivisionStep> steps = new ArrayList<>();
        DivisionResult divisionResult;

        steps.add(DivisionStep.builder()
            .setLocalDividend(12)
            .setLocalDivisor(12)
            .setLocalQuotient(0).build());
        steps.add(DivisionStep.builder()
            .setLocalDividend(34)
            .setLocalDivisor(32)
            .setLocalQuotient(2).build());

        divisionResult = DivisionResult.builder()
            .setDividend(dividend)
            .setDivisor(divisor)
            .setQuotient(dividend / divisor)
            .setSteps(steps).build();

        doNothing().when(divisionValidator).validate(dividend, divisor);

        given(divisionMathProvider.provideMathDivision(dividend, divisor)).willReturn(divisionResult);

        given(divisionViewProvider.provideView(divisionResult))
            .willReturn(expectedResult);

        String actualResult = integerDivisionCalculator.calculateDivision(dividend, divisor);
        verify(divisionValidator).validate(anyInt(), anyInt());
        verify(divisionViewProvider).provideView(any(DivisionResult.class));
        verify(divisionMathProvider).provideMathDivision(anyInt(), anyInt());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateDivision_shouldThrowIllegalArgumentExceptionWhenDivisorIsZero() {
        int dividend = 123;
        int divisor = 0;

        doThrow(new IllegalArgumentException("/ by zero"))
            .when(divisionValidator)
            .validate(dividend, 0);

        Exception exception =assertThrows(
            IllegalArgumentException.class, () -> integerDivisionCalculator.calculateDivision(dividend, divisor));

        verifyNoInteractions(divisionMathProvider, divisionViewProvider);

        String expectedMessage = "/ by zero";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
