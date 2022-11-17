package ua.foxminded.longdivision;

import ua.foxminded.longdivision.provider.DivisionMathProvider;
import ua.foxminded.longdivision.provider.DivisionMathProviderImpl;
import ua.foxminded.longdivision.provider.DivisionViewProvider;
import ua.foxminded.longdivision.provider.DivisionViewProviderImpl;
import ua.foxminded.longdivision.validator.DivisionValidator;
import ua.foxminded.longdivision.validator.DivisionValidatorImpl;

public class IntegerDivisionConsoleApplication {
    public static void main(String[] args) {
        DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();
        DivisionValidator divisionValidator = new DivisionValidatorImpl();
        DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();

        IntegerDivisionCalculator integerDivisionCalculator =
            new IntegerDivisionCalculator(divisionValidator,divisionMathProvider, divisionViewProvider);

        int dividend = 78945;

        int divisor = 4;

        String result = integerDivisionCalculator.calculateDivision(dividend, divisor);
        System.out.println("Result: ");
        System.out.println(result);
    }
}
