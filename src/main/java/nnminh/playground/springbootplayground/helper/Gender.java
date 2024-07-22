package nnminh.playground.springbootplayground.helper;

public enum Gender {
    MALE("Male"),
    FEMALE("FEMALE");

    private final String displayValue;

    Gender(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public String toString() {
        return this.displayValue;
    }
}
