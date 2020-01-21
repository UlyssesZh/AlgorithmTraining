/*
ID: ulysses4
LANG: JAVA
PROG: moobuzz
*/
import java.io.*;
import java.util.*;
public class moobuzz {
	private static final String PROG = "moobuzz";
	
	private static void actualMain() {
		int N = scanner.nextInt();
		int[] numbers = new int[]{1, 2, 4, 7, 8, 11, 13, 14};
		int period = numbers.length;
		int count = N / period;
		int index = N % period - 1;
		if (index < 0) {
			index = period - 1;
			count--;
		}
		out.println(15 * count + numbers[index]);
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
