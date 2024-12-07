package studia.Utils;

public class TrailingZeros {
    public static String addTrailingZeros(int number, int length) {
        String numberString = Integer.toString(number);
        while (numberString.length() < length) {
            numberString = "0" + numberString;
        }
        return numberString;
    }
}
