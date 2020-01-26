/*
ID: ulysses4
LANG: JAVA
PROG: preface
*/
import java.io.*;
import java.util.*;
public class preface {
	private static final String PROG = "preface";
	
	private static final int[] deltaI = new int[] {0, 1, 2, 3, 1, 0, 1, 2, 3, 1};
	private static final int[] deltaV = new int[] {0, 0, 0, 0, 1, 1, 1, 1, 1, 0};
	private static final int[] deltaX = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
	private static final String chars = "IVXLCDM";
	private static void parsePreface(int n, int[] buffer) {
		int last = n % 10;
		buffer[0] += deltaI[last];
		buffer[1] += deltaV[last];
		buffer[2] += deltaX[last];
		n /= 10;
		last = n % 10;
		buffer[2] += deltaI[last];
		buffer[3] += deltaV[last];
		buffer[4] += deltaX[last];
		n /= 10;
		last = n % 10;
		buffer[4] += deltaI[last];
		buffer[5] += deltaV[last];
		buffer[6] += deltaX[last];
		n /= 10;
		last = n % 10;
		buffer[6] += deltaI[last];
	}
	private static void actualMain() {
		int[] result = new int[7];
		int n = scanner.nextInt();
		for (int i = 1; i <= n; i++) parsePreface(i, result);
		for (int i = 0; i < 7 && result[i] > 0; i++)
			out.printf("%c %d\n", chars.charAt(i), result[i]);
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
