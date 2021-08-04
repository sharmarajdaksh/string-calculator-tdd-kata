import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;


class StringCalculatorTest {

    class AddTestCase {
        private final String input;
        private final int expected;

        AddTestCase(String input, int expected) {
            this.input = input;
            this.expected = expected;
        }


        public String getInput() {
            return input;
        }

        public int getExpected() {
            return expected;
        }
    }

    @org.junit.jupiter.api.Test
    void add() {
        List<AddTestCase> testCases = new ArrayList<>();
        testCases.add(new AddTestCase( "", 0));
        testCases.add(new AddTestCase("0", 0));
        testCases.add(new AddTestCase("5", 5));
        testCases.add(new AddTestCase("2,3", 5));
        testCases.add(new AddTestCase("4,5", 9));
        testCases.add(new AddTestCase("4,6,5", 15));
        testCases.add(new AddTestCase("4,9,5", 18));

        StringCalculator calculator = new StringCalculator();
        for (AddTestCase testCase: testCases) {
            Assertions.assertEquals(calculator.add(testCase.getInput()), testCase.getExpected());
        }
    }
}