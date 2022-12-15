package ua.foxminded.longdivision.validator;

public class DivisionValidatorImpl implements DivisionValidator{
    @Override
    public void validate(int dividend, int divisor) {
        if (divisor == 0){
            throw new IllegalArgumentException("/ by zero");
        }
        if(dividend < 0 || divisor < 0){
            throw new IllegalArgumentException("dividend < 0 and/or divisor < 0");
        }
    }
}
