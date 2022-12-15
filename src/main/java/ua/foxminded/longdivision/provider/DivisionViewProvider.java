package ua.foxminded.longdivision.provider;

import ua.foxminded.longdivision.domain.DivisionResult;

public interface DivisionViewProvider {
    String provideView(DivisionResult divisionResult);
}
