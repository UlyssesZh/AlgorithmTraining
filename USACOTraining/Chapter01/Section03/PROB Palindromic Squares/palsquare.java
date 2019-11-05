/*
ID: ulysses4
LANG: JAVA
PROG: palsquare
*/
import java.io.*;
import java.util.*;
public class palsquare {
	private static final String PROG = "palsquare";
	
	private static void actualMain(String[] args) throws IOException {
		int B = scanner.nextInt();
		for (int N = 1; N <= 300; N++) {
			int squared = N * N;
			String string = Integer.toString(squared, B);
			StringBuilder stringBuilder = new StringBuilder(string);
			stringBuilder.reverse();
			String reversedString = stringBuilder.toString();
			if (string.equals(reversedString))
				out.printf("%s %s\n", Integer.toString(N, B).toUpperCase(), string.toUpperCase());
		}
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

