import java.util.ArrayList;

public class StringCalculator {
    public static final String DEFAULT_DELIMITER = ",";

    /**
     * @param string : A string of delimiters in the format [DELIMITER][....] to be parsed for delimiters
     * @return A list of delimiter strings
     */
    private ArrayList<String> getDelimiters(String string) {
        ArrayList<String> delimiters = new ArrayList<>();
        while (string.length() > 0) {
            delimiters.add(string.substring(1, string.indexOf("]")));
            string = string.substring(1);
            int idx = string.indexOf("[");
            if (idx == -1) return delimiters;
            string = string.substring(string.indexOf("["));
        }
        return delimiters;
    }

    public int add(String numbers) throws IllegalArgumentException {
        String delimiter = DEFAULT_DELIMITER;
        ArrayList<String> delimiters = new ArrayList<>();

        if (numbers.startsWith("//")) {
            int endOfDelimitersIdx = numbers.indexOf("\n");

            delimiters = getDelimiters(numbers.substring(2, endOfDelimitersIdx));
            if (delimiters.size() > 0) {
                delimiter = delimiters.get(0);
            }

            // Exclude delimiter specification from numbers to be added
            numbers = numbers.substring(endOfDelimitersIdx + 1);
        }

        // \n can be treated as just another delimiter
        delimiters.add("\n");

        // Replace all delimiters to be the same
        for (String s: delimiters) {
            numbers = numbers.replace(s, delimiter);
        }

        // Split string on the single delimiter
        String[] numbersList = numbers.split(delimiter);

        // Empty string
        if (numbersList.length < 1)
            return 0;

        // Single digit of blank string
        if (numbersList.length == 1) {
            if (numbersList[0].strip() == "") return 0;
            return Integer.parseInt(numbersList[0]);
        }

        int sum = 0;
        // Maintain list of seen negative numbers
        ArrayList<Integer> negatives = new ArrayList<>();
        for (String s: numbersList) {
            int number = Integer.parseInt(s.strip());
            if (number < 0) negatives.add(number);

            // Exclude numbers > 1000 from sum
            if (number <= 1000)
                sum += number;
        }

        // In case of any negative numbers throw exception
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
