package ua.foxminded.longdivision;

import ua.foxminded.longdivision.domain.DivisionResult;
import ua.foxminded.longdivision.provider.DivisionMathProvider;
import ua.foxminded.longdivision.provider.DivisionViewProvider;
import ua.foxminded.longdivision.validator.DivisionValidator;

public class IntegerDivisionCalculator {
    private final DivisionValidator divisionValidator;
    private final DivisionMathProvider divisionMathProvider;
    private final DivisionViewProvider divisionViewProvider;

    public IntegerDivisionCalculator( DivisionValidator divisionValidator,
                                      DivisionMathProvider divisionMathProvider,
                                      DivisionViewProvider divisionViewProvider) {
        this.divisionValidator = divisionValidator;
        this.divisionMathProvider = divisionMathProvider;
        this.divisionViewProvider = divisionViewProvider;
    }

    public String calculateDivision(int dividend, int divisor) {
        divisionValidator.validate(dividend, divisor);

        DivisionResult divisionResult = divisionMathProvider.provideMathDivision(dividend, divisor);

        return divisionViewProvider.provideView(divisionResult);
    }
}
