package ua.foxminded.longdivision.domain;

import java.util.ArrayList;
import java.util.Objects;

public class DivisionResult {

    private final ArrayList<DivisionStep> steps;
    private final int dividend;
    private final int divisor;
    private final int quotient;

    private DivisionResult(Builder builder) {
        this.steps = builder.steps;
        this.dividend = builder.dividend;
        this.divisor = builder.divisor;
        this.quotient = builder.quotient;
    }

    public static Builder builder(){
        return new Builder();
    }

    public ArrayList<DivisionStep> getSteps() {
        return steps;
    }

    public int getDividend() {
        return dividend;
    }

    public int getDivisor() {
        return divisor;
    }

    public int getQuotient() {
        return quotient;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }

        if (o.getClass() != this.getClass()){
            return false;
        }

        final DivisionResult other = (DivisionResult) o;

        return dividend == other.dividend &&
            divisor == other.divisor &&
            quotient == other.quotient &&
            steps.equals(other.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps, dividend, divisor, quotient);
    }

    @Override
    public String toString() {
        return "DivisionResult{" +
            "steps=" + steps +
            ", dividend=" + dividend +
            ", divisor=" + divisor +
            ", quotient=" + quotient +
            '}';
    }

    public static class Builder{

        private  ArrayList<DivisionStep> steps;
        private int dividend;
        private int divisor;
        private int quotient;

        private Builder(){

        }

        public Builder setDividend(int dividend){
            this.dividend = dividend;
            return this;
        }

        public Builder setDivisor(int divisor){
            this.divisor = divisor;
            return this;
        }

        public Builder setQuotient(int quotient){
            this.quotient = quotient;
            return this;
        }

        public Builder setSteps(ArrayList<DivisionStep> steps){
            this.steps = steps;
            return this;
        }

        public DivisionResult build(){
            return new DivisionResult(this);
        }
    }
}
