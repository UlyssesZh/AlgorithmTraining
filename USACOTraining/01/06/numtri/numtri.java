/*
ID: ulysses4
LANG: JAVA
PROG: numtri
*/
import java.io.*;
import java.util.*;
public class numtri {
	private static final String PROG = "numtri";
	
	private static void actualMain(String[] args) throws IOException {
		int R = scanner.nextInt();
		int[] s = new int[R];
		int[] n = new int[R];
		for (int i = 0, i1 = i - 1, i2 = i - 2; i < R; i++, i1++, i2++) {
			for (int j = 0; j <= i; j++) n[j] = scanner.nextInt();
			if (i > 0) s[i] = s[i1] + n[i];
			for (int j = i1, j1 = i2; j > 0; j--, j1--)
				s[j] = Math.max(s[j], s[j1]) + n[j];
			s[0] += n[0];
		}
		out.println(Arrays.stream(s).summaryStatistics().getMax());
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
