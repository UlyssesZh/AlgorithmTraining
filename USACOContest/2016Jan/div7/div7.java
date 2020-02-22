import java.io.*;
import java.util.*;
public class div7 {
	private static final String PROG = "div7";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		int[] sum = new int[n + 1];
		for (int i = 1; i <= n; i++)
			sum[i] = (sum[i - 1] + scanner.nextInt()) % 7;
		for (int r = n; r >= 0; r--) {
			for (int i = 0; i < n - r; i++) {
				if (sum[i] == sum[i + r]) {
					out.println(r);
					return;
				}
			}
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
