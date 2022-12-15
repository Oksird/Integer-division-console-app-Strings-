package ua.foxminded.longdivision.provider;

import org.junit.jupiter.api.Test;
import ua.foxminded.longdivision.domain.DivisionResult;
import ua.foxminded.longdivision.domain.DivisionStep;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionMathProviderImplTest {

    private final DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();

    @Test
    void provideMathDivision_shouldReturnDivisionResultWithMathematicallyCorrectFields() {
        ArrayList<DivisionStep> expectedSteps = new ArrayList<>();

        expectedSteps.add(DivisionStep.builder()
            .setLocalDividend(12)
            .setLocalDivisor(12)
            .setLocalQuotient(0).build());
        expectedSteps.add(DivisionStep.builder()
            .setLocalDividend(34)
            .setLocalDivisor(32)
            .setLocalQuotient(2).build());

        DivisionResult expectedResult = divisionMathProvider.provideMathDivision(1234, 4);
        assertEquals(expectedResult,
            DivisionResult.builder()
            .setDividend(1234)
            .setDivisor(4)
            .setQuotient(1234/4)
            .setSteps(expectedSteps).build());
    }

    @Test
    void provideMathDivision_shouldReturnDivisionResultWithQuotientZeroAndEmptyStepsWhenDividendIsZero() {
        DivisionResult expectedResult = divisionMathProvider.provideMathDivision(0,4531245);
        assertEquals(expectedResult,
            DivisionResult.builder()
            .setDividend(0)
            .setDivisor(4531245)
            .setQuotient(0)
            .setSteps(new ArrayList<>()).build());
    }
}
