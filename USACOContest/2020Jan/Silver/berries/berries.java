import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class berries {
	private static final String PROG = "meetings";
	private static void actualMain() {
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		int[] B = new int[N];
		for (int i = 0; i < N; i++) B[i] = scanner.nextInt();
		
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
