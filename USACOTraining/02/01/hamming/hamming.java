/*
ID: ulysses4
LANG: JAVA
PROG: hamming
*/
import java.io.*;
import java.util.*;
public class hamming {
	private static final String PROG = "hamming";
	
	private static int n;
	private static int d;
	private static int max;
	private static int[] found;
	private static int[][] edges;
	private static class SearchFinished extends RuntimeException {}
	private static int hammingDistance(int a, int b) {
		int result = 0;
		int comparison = a ^ b;
		for (int bit = 1; bit < max; bit <<= 1)
			if ((comparison & bit) != 0) result++;
		return result;
	}
	private static void init() {
		n = scanner.nextInt();
		max = 1 << scanner.nextInt();
		d = scanner.nextInt();
		found = new int[n];
		edges = new int[max][max];
		for (int a = 0; a < max; a++) for (int b = 0; b < max; b++)
			edges[a][b] = hammingDistance(a, b);
		found[0] = 0;
	}
	private static void dfs(int foundN, int begin) throws SearchFinished {
		if (foundN == n) throw new SearchFinished();
		canUse:
		for (int a = begin; a < max; a++) {
			for (int i = 0; i < foundN; i++)
				if (edges[found[i]][a] < d) continue canUse;
			found[foundN++] = a++;
			dfs(foundN, a);
		}
	}
	private static void output() {
		for (int i = 0; i < n; i++) {
			if (i % 10 != 0) out.print(' ');
			out.print(found[i]);
			if (i % 10 == 9 || i == n - 1) out.println();
		}
	}
	private static void actualMain() {
		init();
		try {
			dfs(1, 1);
		} catch (SearchFinished e) {
			output();
		}
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
