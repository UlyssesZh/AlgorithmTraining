/*
ID: ulysses4
LANG: JAVA
PROG: gymnastics
*/
import java.io.*;
import java.util.*;
public class gymnastics {
	private static final String PROG = "gymnastics";
	
	private static void actualMain() {
		int K = scanner.nextInt();
		int N = scanner.nextInt();
		int[][] consistency = new int[N + 1][N + 1];
		for (int i = 0; i < K; i++) {
			int[] ranking = new int[N];
			for (int j = 0; j < N; j++) {
				ranking[j] = scanner.nextInt();
				for (int k = 0; k <= j; k++) {
					consistency[ranking[k]][ranking[j]]++;
				}
			}
		}
		int ret = 0;
		for (int i = 1; i <= N; i++) {
			for (int k = 1; k <= N; k++) {
				if (consistency[k][i] == 0) {
					ret++;
				}
			}
		}
		out.println(ret);
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
