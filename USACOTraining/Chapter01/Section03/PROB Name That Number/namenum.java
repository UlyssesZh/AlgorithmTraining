/*
ID: ulysses4
LANG: JAVA
PROG: namenum
*/
import java.io.*;
import java.util.*;
public class namenum {
	private static final String PROG = "namenum";
	
	private static final char[][] CHARS = new char[][] {
		new char[0], new char[0],
		new char[] {'A', 'B', 'C'},
		new char[] {'D', 'E', 'F'},
		new char[] {'G', 'H', 'I'},
		new char[] {'J', 'K', 'L'},
		new char[] {'M', 'N', 'O'},
		new char[] {'P', 'R', 'S'},
		new char[] {'T', 'U', 'V'},
		new char[] {'W', 'X', 'Y'}
	};
	private static final String[] DICT = new String[5000];
	static {
		try {
			Scanner dict = new Scanner(new FileInputStream("dict.txt"));
			for (int i = 0; dict.hasNext(); i++)
				DICT[i] = dict.next();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static boolean nameMeetNumber(String name, int[] digits) {
		if (digits.length != name.length()) return false;
		for (int i = 0; i < digits.length; i++)
			if (!charMeetDigit(name.charAt(i), digits[i])) return false;
		return true;
	}
	private static boolean charMeetDigit(char aChar, int digit) {
		for (int i = 0; i < 3; i++)
			if (CHARS[digit][i] == aChar) return true;
		return false;
	}
	private static void actualMain(String[] args) throws IOException {
		String numberString = scanner.next();
		int[] digits = new int[numberString.length()];
		for (int i = 0; i < numberString.length(); i++)
			digits[i] = numberString.charAt(i) - '0';
		boolean none = true;
		for (int i = 0; DICT[i] != null; i++)
			if (nameMeetNumber(DICT[i], digits)) {
				out.println(DICT[i]);
				none = false;
			}
		if (none) out.println("NONE");
	}
	
	private static InputStream in;
	private static PrintStream out;
	private static Scanner scanner;
	public static void main(String[] args) throws IOException {
		in = new FileInputStream(PROG + ".in");
		out = new PrintStream(PROG + ".out");
		scanner = new Scanner(in);
		actualMain(args);
		in.close();
		out.close();
		scanner.close();
	}
}

