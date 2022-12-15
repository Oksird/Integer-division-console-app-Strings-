package ua.foxminded.longdivision.domain;

import java.util.Objects;

public class DivisionStep {

    private final int localDividend;
    private final int localDivisor;
    private final int localQuotient;

    private DivisionStep(Builder builder) {
        localDividend = builder.localDividend;
        localDivisor = builder.localDivisor;
        localQuotient = builder.localQuotient;
    }

    public static Builder builder(){
        return new Builder();
    }

    public int getLocalDividend() {
        return localDividend;
    }

    public int getLocalDivisor() {
        return localDivisor;
    }

    public int getLocalQuotient() {
        return localQuotient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o.getClass() != this.getClass()){
            return false;
        }

        final DivisionStep other = (DivisionStep) o;

        return localDividend == other.localDividend &&
            localDivisor == other.localDivisor &&
            localQuotient == other.localQuotient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDividend, localDivisor, localQuotient);
    }

    @Override
    public String toString() {
        return "DivisionStep{" +
            "localDividend=" + localDividend +
            ", localDivisor=" + localDivisor +
            ", localQuotient=" + localQuotient +
            '}';
    }

    public static class Builder {
        private int localDividend;
        private int localDivisor;
        private int localQuotient;

        private Builder(){

        }

        public Builder setLocalDividend(int localDividend){
            this.localDividend = localDividend;
            return this;
        }

        public Builder setLocalDivisor(int localDivisor){
            this.localDivisor = localDivisor;
            return this;
        }

        public Builder setLocalQuotient(int localQuotient){
            this.localQuotient = localQuotient;
            return this;
        }

        public DivisionStep build(){
            return new DivisionStep(this);
        }
    }
}
