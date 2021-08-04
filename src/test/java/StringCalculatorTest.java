import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;


class StringCalculatorTest {
    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @org.junit.jupiter.api.Test
    void addSingleDigit() {
        Assertions.assertEquals(0, calculator.add("0"));
        Assertions.assertEquals(5, calculator.add("5"));
    }

    @org.junit.jupiter.api.Test
    void addEmptyString() {
        Assertions.assertEquals(0, calculator.add(""));
        Assertions.assertEquals(0, calculator.add(" "));
    }

    @org.junit.jupiter.api.Test
    void addUnknownNumberOfIntegers() {
        Assertions.assertEquals(9, calculator.add("5,4"));
        Assertions.assertEquals(9, calculator.add("1,4,4"));
        Assertions.assertEquals(10, calculator.add("1,4,3,2"));
    }

    @org.junit.jupiter.api.Test
    void addNewLineInput() {
        Assertions.assertEquals(9, calculator.add("5\n4"));
        Assertions.assertEquals(10, calculator.add("1,4\n3,2"));
    }

    @org.junit.jupiter.api.Test
    void addUsingCustomDelimiter() {
        Assertions.assertEquals(9, calculator.add("//[;]\n5\n4"));
        Assertions.assertEquals(10, calculator.add("//[;]\n1;4\n3;2"));
    }

    @org.junit.jupiter.api.Test
    void addUsingCustomLongDelimiter() {
        Assertions.assertEquals(9, calculator.add("//[nn]\n5nn4"));
    }

    @org.junit.jupiter.api.Test
    void addThrowOnNegativeIntegers() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.add("//[;]\n-15\n4"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.add("//[;]\n-15\n-4;-10"));
    }

    @org.junit.jupiter.api.Test
    void addIgnoreNumbersGreaterThan1000() {
        Assertions.assertEquals(4, calculator.add("//[;]\n1005\n4"));
        Assertions.assertEquals(5, calculator.add("//[nn]\n5nn1004"));
        Assertions.assertEquals(10, calculator.add("//[;]\n1;4\n3;2;100999"));
    }

    @org.junit.jupiter.api.Test
    void addMultipleDelimiters() {
        Assertions.assertEquals(4, calculator.add("//[;]\n1005\n4"));
        Assertions.assertEquals(24, calculator.add("//[nn][;]\n5nn1004;9;10"));
        Assertions.assertEquals(10, calculator.add("//[nn][**]\n1nn4\n3**2nn100999"));
    }


}