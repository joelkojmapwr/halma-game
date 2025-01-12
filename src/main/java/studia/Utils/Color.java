package studia.Utils;

public class Color {
	public static boolean YinYan = false;
	public final static String[] names = {"Red", "Green", "Yellow", "Blue", "Purple", "LightBlue"};
	public final static String[] names2 = {"Black", "White"};
	public static String colorName(int i) {
		return YinYan ? names2[i] : names[i];
	}
}
