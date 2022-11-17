package ua.foxminded.longdivision.provider;

import ua.foxminded.longdivision.domain.DivisionResult;
import ua.foxminded.longdivision.domain.DivisionStep;

import java.util.ArrayList;

public class DivisionMathProviderImpl implements DivisionMathProvider {

    @Override
    public DivisionResult provideMathDivision(int dividend, int divisor) {
        ArrayList<DivisionStep> steps = new ArrayList<>();
        int quotient = dividend / divisor;
        int localDividend;
        int localDivisor;
        int localQuotient = 0;

        String[] digitsOfDividend = String.valueOf(dividend).split("");

        StringBuilder localDividendString = new StringBuilder();

        for (int i = 0; i < digitsOfDividend.length; i ++) {

            if (localQuotient!= 0 && dividend < divisor && dividend != 0){
                localDividend = localQuotient;
            }else {
                localDividendString.append(digitsOfDividend[i]);
                localDividend = Integer.parseInt(localDividendString.toString());
            }

            if (dividend < divisor && dividend != 0 && localDividend < divisor){
                while(localDividend < divisor){
                    localDividend*=10;
                }
            }

            if (localDividend >= divisor) {
                localQuotient = localDividend % divisor;
                localDivisor = localDividend / divisor * divisor;
                localDividendString.replace(0, localDividendString.length(), String.valueOf(localQuotient));
                steps.add(DivisionStep.builder()
                    .setLocalDividend(localDividend)
                    .setLocalDivisor(localDivisor).
                    setLocalQuotient(localQuotient)
                    .build());
            }
        }

        return DivisionResult.builder()
            .setDividend(dividend)
            .setDivisor(divisor)
            .setQuotient(quotient)
            .setSteps(steps)
            .build();
    }
}
