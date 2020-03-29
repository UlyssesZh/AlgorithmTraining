import java.io.*;
import java.util.*;
public class cowcode {
	private static final String PROG = "cowcode";
	
	private static String string;
	private static int length;
	private static char get(long i) {
		if (i < length) return string.charAt((int) i);
		long div = i / length, mod = i % length;
		div -= Long.highestOneBit(div);
		if (div != 0 || mod != 0) i = div * length + mod;
		return get(i - 1);
	}
	private static void actualMain() {
		string = scanner.next();
		length = string.length();
		out.println(get(scanner.nextLong() - 1));
	}
	
	private static InputStream in;
	private static PrintStream out;
	private static Scanner scanner;
	public static void main(String[] args) throws IOException {
		in = new FileInputStream(PROG + ".in");
		out = new PrintStream(PROG + ".out");
		scanner = new Scanner(in);
		actualMain();
		in.close();
		out.close();
		scanner.close();
	}
}
