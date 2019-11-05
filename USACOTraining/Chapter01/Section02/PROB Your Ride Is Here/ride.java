/*
ID: ulysses4
LANG: JAVA
PROG: ride
*/
import java.io.*;
import java.util.*;
public class ride {
	private static final String PROG = "ride";
	
	private static void actualMain(String[] args) throws IOException {
		out.println(productOf(scanner.next()) == productOf(scanner.next()) ? "GO" : "STAY");
	}
	private static int productOf(String string) {
		int result = 1;
		for (int i = 0; i < string.length(); i++)
			result *= string.charAt(i) - 'A' + 1;
		return result % 47;
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

