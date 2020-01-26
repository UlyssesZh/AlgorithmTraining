/*
ID: ulysses4
LANG: JAVA
PROG: runround
*/
import java.io.*;
import java.util.*;
public class runround {
	private static final String PROG = "runround";
	
	private static int digitAt(String string, int n) {
		return string.charAt(n) - '0';
	}
	private static boolean isRunaround(int n) {
		String string = Integer.toString(n);
		int length = string.length();
		boolean[] marks = new boolean[10];
		marks[digitAt(string, 0)] = true;
		for (int i = 0, c = 1;; c++) {
			i = (i + digitAt(string, i)) % length;
			if (marks[digitAt(string, i)])
				return i == 0 && c == length;
			else marks[digitAt(string, i)] = true;
		}
	}
	private static void actualMain() {
		int N = scanner.nextInt() + 1;
		while (!isRunaround(N)) N++;
		out.println(N);
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
