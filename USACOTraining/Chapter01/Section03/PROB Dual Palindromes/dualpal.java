/*
ID: ulysses4
LANG: JAVA
PROG: dualpal
*/
import java.io.*;
import java.util.*;
public class dualpal {
	private static final String PROG = "dualpal";
	
	private static boolean isPalindrome(String string) {
		StringBuilder stringBuilder = new StringBuilder(string);
		stringBuilder.reverse();
		String reversedString = stringBuilder.toString();
		return string.equals(reversedString);
	}
	private static boolean isPalindrome(int n, int b) {
		return isPalindrome(Integer.toString(n, b));
	}
	private static boolean isDualPalindrome(int n) {
		boolean already = false;
		for (int b = 2; b <= 10; b++)
			if (isPalindrome(n, b))
				if (already)
					return true;
				else
					already = true;
		return false;
	}
	private static void actualMain(String[] args) throws IOException {
		int found = 0;
		int N = scanner.nextInt();
		int S = scanner.nextInt();
		for (int n = S + 1; found < N; n++)
			if (isDualPalindrome(n)) {
				out.println(n);
				found++;
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

