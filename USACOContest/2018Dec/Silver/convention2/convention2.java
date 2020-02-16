import java.io.*;
import java.util.*;
public class convention2 {
	private static final String PROG = "convention2";
	
	private static int n;
	private static int[] arrivals;
	private static int[] eatTime;
	private static boolean[] waiting;
	private static Integer[] approaching;
	private static int approachingI;
	private static int now;
	private static int eating;
	private static int[] startTime;
	private static int result;
	private static void init() {
		n = scanner.nextInt();
		arrivals = new int[n];
		eatTime = new int[n];
		waiting = new boolean[n];
		approaching = new Integer[n];
		startTime = new int[n];
		for (int i = 0; i < n; i++) {
			arrivals[i] = scanner.nextInt();
			eatTime[i] = scanner.nextInt();
			approaching[i] = i;
			startTime[i] = -1;
		}
		Arrays.sort(approaching, Comparator.comparingInt(i -> arrivals[i]));
		result = 0;
		approachingI = 0;
		eating = -1;
	}
	private static int nextArriveTime() {
		return arrivals[approaching[approachingI]];
	}
	private static int nextFinishEatingTime() {
		return eating == -1 ? -1 : startTime[eating] + eatTime[eating];
	}
	private static int findQueueTop() {
		for (int i = 0; i < n; i++) if (waiting[i]) return i;
		return -1;
	}
	private static void eat() {
		int next = findQueueTop();
		if (next != -1) {
			waiting[next] = false;
			startTime[next] = now;
			eating = next;
			result = Math.max(result, now - arrivals[next]);
		}
	}
	private static void emulate() {
		while (approachingI < n) {
			if (eating == -1) eat();
			int arrive = nextArriveTime();
			int finishing = nextFinishEatingTime();
			if (finishing == -1 || arrive < finishing) {
				now = arrive;
				waiting[approaching[approachingI++]] = true;
			} else {
				now = finishing;
				eating = -1;
			}
		}
	}
	private static void actualMain() {
		init();
		emulate();
		out.println(result);
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
