package studia.Utils;

public class TrailingZeros {
    /**
     * Adds trailing zeros to a number
     * @param number
     * @param length
     * @return
     */
    public static String addTrailingZeros(int number, int length) {
        String numberString = Integer.toString(number);
        while (numberString.length() < length) {
            numberString = "0" + numberString;
        }
        return numberString;
    }
}
