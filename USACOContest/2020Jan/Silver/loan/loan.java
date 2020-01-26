import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class loan {
	private static final String PROG = "loan";
	private static long N, K, M, X;
	private static boolean ok() {
		for (long remain = N, k = K; k > 0; k--)
			if (M * k >= remain) return true;
			else {
				long y = remain / X;
				if (y < M) return false;
				remain -= y;
				if (remain <= 0) return true;
			}
		return false;
	}
	private static void actualMain() {
		N = scanner.nextLong();
		K = scanner.nextLong();
		M = scanner.nextLong();
		long inf = 1;
		long sup = N / M + 1;
		while (true) {
			X = (inf + sup) / 2;
			//out.printf("X=%d, inf=%d, sup=%d\n", X, inf, sup);
			if (X == inf) {
				out.println(X);
				break;
			}
			if (ok()) inf = X;
			else sup = X;
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
