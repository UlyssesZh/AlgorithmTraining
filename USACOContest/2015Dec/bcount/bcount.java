import java.io.*;
import java.util.*;
public class bcount {
	private static final String PROG = "bcount";
	
	private static int q;
	private static int[][] sum;
	private static void init() {
		int n = scanner.nextInt();
		q = scanner.nextInt();
		sum = new int[n + 1][4];
		for (int i = 1; i <= n; i++) {
			int breed = scanner.nextInt();
			for (int b = 1; b <= 3; b++)
				sum[i][b] = sum[i - 1][b] + (b == breed ? 1 : 0);
		}
	}
	private static void output(int a, int b, int breed) {
		out.print(sum[b][breed] - sum[a - 1][breed]);
	}
	private static void output() {
		while (q-- > 0) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			output(a, b, 1);
			out.print(' ');
			output(a, b, 2);
			out.print(' ');
			output(a, b, 3);
			out.println();
		}
	}
	private static void actualMain() {
		init();
		output();
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
