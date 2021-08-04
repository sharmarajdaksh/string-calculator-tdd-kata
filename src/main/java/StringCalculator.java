import java.util.ArrayList;

public class StringCalculator {
    private ArrayList<String> getDelimiters(String delimitersString) {
        ArrayList<String> delimiters = new ArrayList<>();
        while (delimitersString.length() > 0) {
            delimiters.add(delimitersString.substring(1, delimitersString.indexOf("]")));
            delimitersString = delimitersString.substring(1);
            int idx = delimitersString.indexOf("[");
            if (idx == -1) return delimiters;
            delimitersString = delimitersString.substring(delimitersString.indexOf("["));
        }
        return delimiters;
    }

    public int add(String numbers) throws IllegalArgumentException {
        String delimiter = ",";
        ArrayList<String> delimiters = new ArrayList<>();

        if (numbers.startsWith("//")) {
            int idx = numbers.indexOf("\n");
            String delimitersString = numbers.substring(2, idx);
            delimiters = getDelimiters(delimitersString);
            numbers = numbers.substring(idx + 1);
        }

        if (delimiters.size() > 0) {
            delimiter = delimiters.get(0);
        }
        delimiters.add("\n");

        for (String s: delimiters) {
            numbers = numbers.replace(s, delimiter);
        }
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
