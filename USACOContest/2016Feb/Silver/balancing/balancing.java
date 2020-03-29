import java.io.*;
import java.util.*;
public class balancing {
	private static final String PROG = "balancing";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static class Cow {
		private int x, y;
		private Cow() {
			x = scanner.nextInt();
			y = scanner.nextInt();
		}
	}
	private static Cow[] cows;
	private static void init() {
		cows = new Cow[scanner.nextInt()];
		for (int i = 0; i < cows.length; i++)
			cows[i] = new Cow();
	}
	private static int calcM(Cow cow1, Cow cow2) {
		if (cow1.x < cow2.x || cow1.y > cow2.y)
			return INFINITY;
		int[] partitions = new int[4];
		for (Cow o : cows) {
			int i = 0;
			if (o.x <= cow1.x) i += 1;
			if (o.y <= cow2.y) i += 2;
			partitions[i]++;
		}
		int result = 0;
		for (int partition : partitions)
			result = Math.max(result, partition);
		return result;
	}
	private static int calc() {
		int result = Integer.MAX_VALUE / 2;
		for (Cow cow1 : cows) for (Cow cow2 : cows)
			result = Math.min(result, calcM(cow1, cow2));
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
