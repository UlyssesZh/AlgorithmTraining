import java.io.*;
import java.util.*;
public class maxcross {
	private static final String PROG = "maxcross";
	
	private static int n;
	private static int k;
	private static int[] sum;
	private static void init() {
		n = scanner.nextInt();
		k = scanner.nextInt();
		boolean[] broken = new boolean[n];
		sum = new int[n + 1];
		for (int b = scanner.nextInt(); b > 0; b--) {
			broken[scanner.nextInt() - 1] = true;
		}
		for (int i = 0; i < n; i++) {
			sum[i + 1] = sum[i] + (broken[i] ? 1 : 0);
		}
	}
	private static int calc() {
		int result = Integer.MAX_VALUE / 2;
		for (int i = 0; i <= n - k; i++) {
			result = Math.min(result, sum[i + k] - sum[i]);
		}
		return result;
	}
	private static void actualMain() {
		init();
		out.println(calc());
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
