package ua.foxminded.longdivision.provider;

import ua.foxminded.longdivision.domain.DivisionResult;

public interface DivisionMathProvider {
    DivisionResult provideMathDivision(int dividend, int divisor);
}
