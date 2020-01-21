/*
ID: ulysses4
LANG: JAVA
PROG: barn1
*/
import java.io.*;
import java.util.*;
public class barn1 {
	private static final String PROG = "barn1";

	private static void actualMain() {
		int M = scanner.nextInt(); // max number of boards
		int S = scanner.nextInt(); // number of stalls
		int C = scanner.nextInt(); // number of cows
		if (C <= M) {
			out.println(C);
			return;
		}
		int[] stalls = new int[C]; // occupied stalls
		for (int i = 0; i < C; i++)
			stalls[i] = scanner.nextInt();
		Arrays.sort(stalls);
		int[] dists = new int[C - 1]; // distances between stalls,
		                              // INF if connected with board
		for (int i = 0; i < C - 1; i++)
			dists[i] = stalls[i + 1] - stalls[i];
		int result = C;
		for (int i = M; i < C; i++) {
			int min = dists[0];
			int minJ = 0;
			for (int j = 1; j < i; j++)
				if (dists[j] < min) {
					min = dists[j];
					minJ = j;
				}
			result += dists[minJ] - 1;
			dists[minJ] = Integer.MAX_VALUE;
		}
		out.println(result);
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
