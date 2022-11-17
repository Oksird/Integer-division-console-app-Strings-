package ua.foxminded.longdivision.provider;

import org.junit.jupiter.api.Test;
import ua.foxminded.longdivision.domain.DivisionResult;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionViewProviderImplTest {

    private final DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();

    @Test
    void provideView_shouldReturnStringWithCorrectColumnDivisionWhenDividendAndDivisorArePositiveNaturalDigits() {
        String expectedResult =
            """
                _78945│4
                 4    │-----
                 -    │19736
                _38
                 36
                 --
                 _29
                  28
                  --
                  _14
                   12
                   --
                   _25
                    24
                    --
                     1""";
        DivisionResult divisionResult = new DivisionMathProviderImpl().provideMathDivision(78945, 4);
        assertEquals(expectedResult, divisionViewProvider.provideView(divisionResult));
    }

    @Test
    void provideView_shouldReturnOneLineWithResultWhenDividendIsZero() {
        String expectedResult =
            """
                0│14
                 │--
                 │0""";
        DivisionResult divisionResult = new DivisionMathProviderImpl().provideMathDivision(0, 14);
        assertEquals(expectedResult, divisionViewProvider.provideView(divisionResult));
    }

    @Test
    void provideView_shouldReturnOneLineWithResultWhenDividendIsLessThenTheDivisor() {
        String expectedResult =
            """
                _15│25
                 15│--
                 --│0
                  0""";
        DivisionResult divisionResult = new DivisionMathProviderImpl().provideMathDivision(15, 25);
        assertEquals(expectedResult, divisionViewProvider.provideView(divisionResult));
    }
}
