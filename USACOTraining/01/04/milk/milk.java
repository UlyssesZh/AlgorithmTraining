/*
ID: ulysses4
LANG: JAVA
PROG: milk
*/
import java.io.*;
import java.util.*;
public class milk {
	private static final String PROG = "milk";
	
	private static void actualMain() {
		int N = scanner.nextInt(); // requirement
		int M = scanner.nextInt(); // farmers number
		int[] P = new int[M]; // prices
		int[] A = new int[M]; // amount provided
		Integer[] indices = new Integer[M];
		for (int i = 0; i < M; i++) {
			P[i] = scanner.nextInt();
			A[i] = scanner.nextInt();
			indices[i] = i;
		}
		Arrays.sort(indices, (o1, o2) -> Integer.compare(P[o1], P[o2]));
		int bought = 0;
		int spent = 0;
		for (int i = 0; i < M; i++) {
			int index = indices[i];
			if (bought + A[index] > N) {
				spent += P[index] * (N - bought);
				break;
			} else {
				bought += A[index];
				spent += P[index] * A[index];
			}
		}
		out.println(spent);
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
