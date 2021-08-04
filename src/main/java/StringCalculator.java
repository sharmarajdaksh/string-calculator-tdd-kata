import java.util.ArrayList;

public class StringCalculator {
    public int add(String numbers) throws IllegalArgumentException {
        String delimiter = ",";
        if (numbers.startsWith("//")) {
            int idx = numbers.indexOf("\n");
            delimiter = numbers.substring(2, idx);
            numbers = numbers.substring(idx + 1);
        }

        numbers = numbers.replace("\n", delimiter);
        String[] numbersList = numbers.split(delimiter);

        if (numbersList.length < 1)
            return 0;
        if (numbersList.length == 1) {
            if (numbersList[0].strip() == "") return 0;
            return Integer.parseInt(numbersList[0]);
        }

        int sum = 0;
        ArrayList<Integer> negatives = new ArrayList<>();
        for (String s: numbersList) {
            int number = Integer.parseInt(s.strip());
            if (number < 0) negatives.add(number);
            if (number > 1000) continue;
            sum += number;
        }

        if (negatives.size() > 0) {
            String errorString = "negatives not allowed -";
            for (int number: negatives) {
                errorString += " " + number;
            }
            throw new IllegalArgumentException(errorString);
        }

        return sum;
    }
}
