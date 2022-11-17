package ua.foxminded.longdivision.provider;

import ua.foxminded.longdivision.domain.DivisionResult;
import ua.foxminded.longdivision.domain.DivisionStep;

import java.util.ArrayList;

public class DivisionViewProviderImpl implements DivisionViewProvider {

    @Override
    public String provideView(DivisionResult divisionResult) {
        ArrayList<DivisionStep> steps = divisionResult.getSteps();
        int dividend = divisionResult.getDividend();
        int divisor = divisionResult.getDivisor();
        int quotient = divisionResult.getQuotient();

        StringBuilder resultToPrint = new StringBuilder();

        if (dividend == 0) {
            resultToPrint.append(dividend).append("│").append(divisor).append("\n")
                .append(' ').append("│")
                .append(fillString(calculateDigitsInNumber(divisor), '-')).append("\n")
                .append(' ').append("│").append(quotient);
            return resultToPrint.toString();
        }

        if (dividend < divisor) {
            resultToPrint.append("_").append(dividend).append("│").append(divisor).append("\n")
                .append(" ").append(dividend).append("│").append(fillString(calculateDigitsInNumber(divisor),
                    '-')).append("\n")
                .append(fillString(calculateDigitsInNumber(dividend) - 1, ' '))
                .append(fillString(calculateDigitsInNumber(dividend), '-')).append("│").append(quotient).append("\n")
                .append(fillString(calculateDigitsInNumber(dividend), ' ')).append(quotient);

            return resultToPrint.toString();
        }

        for (int i = 0; i < steps.size(); i++) {
            DivisionStep step = steps.get(i);
            int localDividend = step.getLocalDividend();
            int localDivisor = step.getLocalDivisor();
            String localDividendString = String.format("%" + (i + 2) + "s", "_" + localDividend);
            resultToPrint.append(localDividendString).append("\n");
            String localDivisorString = String.format("%" + (i + 2) + "d", localDivisor);
            resultToPrint.append(localDivisorString).append("\n");
            int countOfNeededTabs = localDividendString.length() - calculateDigitsInNumber(localDivisor);
            resultToPrint.append(fillString(countOfNeededTabs, ' ')).append(fillString(calculateDigitsInNumber(localDividend), '-')).append("\n");
        }

        String localQuotientString = String.format("%" + (steps.size() + 1) + "d", steps.get(steps.size() - 1).getLocalQuotient());
        resultToPrint.append(localQuotientString);
        modifyResultToView(dividend, divisor, quotient, resultToPrint);
        return resultToPrint.toString();
    }

    private int calculateDigitsInNumber(int number) {
        return String.valueOf(number).split("").length;
    }

    private String fillString(int numberOfSymbols, char symbol) {
        return String.valueOf(symbol).repeat(Math.max(0, numberOfSymbols));
    }

    private void modifyResultToView(Integer dividend, Integer divisor, int quotient, StringBuilder resultToPrint) {

        int[] index = new int[3];
        for (int i = 0, j = 0; i < resultToPrint.length(); i++) {
            if (resultToPrint.charAt(i) == '\n') {
                index[j] = i;
                j++;
            }
            if (j == index.length) {
                break;
            }
        }

        int tab = calculateDigitsInNumber(dividend) + 1 - index[0];

        if (dividend < divisor) {
            resultToPrint.insert(index[2], fillString(tab, ' ') + "│" + (float) dividend / (float) divisor);
        } else {
            resultToPrint.insert(index[2], fillString(tab, ' ') + "│" + quotient);
        }

        resultToPrint.insert(index[1], fillString(tab, ' ') + "│" + fillString(calculateDigitsInNumber(quotient), '-'));
        resultToPrint.insert(index[0], "│" + divisor);
        resultToPrint.replace(1, index[0], dividend.toString());
    }
}
