public class StringCalculator {
    public int add(String numbers) throws NumberFormatException {
        String[] numbersList = numbers.split(",");

        if (numbersList.length < 1) return 0;
        if (numbersList.length == 1) {
            if (numbersList[0] == "") return 0;
            return Integer.parseInt(numbersList[0]);
        }
        return Integer.parseInt(numbersList[0]) + Integer.parseInt(numbersList[1]);
    }
}
