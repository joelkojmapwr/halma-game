package studia.Utils;

/**
 * Class that holds the color names
 */
public class Color {
	public static boolean YinYan = false;
	public final static String[] names = {"Red", "Green", "Yellow", "Blue", "Purple", "LightBlue"};
	public final static String[] names2 = {"Black", "White"};
	/**
	 * Returns the color name based on the index and the YinYan flag
	 * @param i
	 * @return
	 */
	public static String colorName(int i) {
		return YinYan ? names2[i] : names[i];
	}
}
