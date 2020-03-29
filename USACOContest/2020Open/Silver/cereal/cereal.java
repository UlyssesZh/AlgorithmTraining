import java.io.*;
import java.util.*;
public class cereal {
	private static final String PROG = "cereal";
	
	private static int n;
	private static int m;
	private static Cow[] cows;
	private static Cereal[] cereals;
	private static Set<Integer> eaten;
	private static class Cow {
		private int i;
		private int f;
		private int s;
		private boolean eating;
		private Cereal eatee;
		private Cow(int i, int f, int s) {
			this.i = i;
			this.f = f;
			cereals[f].eaters.offer(this);
			this.s = s;
			cereals[s].eaters.offer(this);
			this.eating = false;
			this.eatee = null;
		}
		private void eat() {
			if (cereals[f].eater == null) {
				eatee = cereals[f];
				eatee.first = true;
				eaten.add(f);
			} else if (cereals[s].eater == null) {
				eatee = cereals[s];
				eatee.first = false;
				eaten.add(s);
			} else {
				eating = false;
				return;
			}
			eatee.eater = this;
			eating = true;
		}
		private int cerealI() {
			return eating ? eatee.first ? f : s : -1;
		}
	}
	private static class Cereal {
		private Cow eater;
		private boolean first;
		private Queue<Cow> eaters;
		private Cereal(Cow eater, boolean first) {
			this.eater = eater;
			this.first = first;
			eaters = new LinkedList<>();
		}
		private static Cereal of(Cow cow, boolean first) {
			return new Cereal(cow, first);
		}
	}
	private static void init() {
		n = scanner.nextInt();
		m = scanner.nextInt();
		cows = new Cow[n];
		cereals = new Cereal[m];
		eaten = new HashSet<>(m);
		for (int i = 0; i < m; i++) {
			cereals[i] = new Cereal(null, false);
		}
		for (int i = 0; i < n; i++) {
			cows[i] = new Cow(
					i,
					scanner.nextInt() - 1,
					scanner.nextInt() - 1
			);
			cows[i].eat();
		}
	}
	private static void deleteCow(int cowI) {
		Cow cow = cows[cowI];
		if (!cow.eating) return;
		int cerealI = cow.cerealI();
		clearEater(cerealI, cowI);
	}
	private static void clearEater(int cerealI, int cowI) {
		Cereal cereal = cereals[cerealI];
		eaten.remove(cerealI);
		cereal.eater = null;
		for (Cow eater : cereal.eaters) {
			if (eater.i <= cowI) continue;
			if (cereals[eater.f].eater == eater) continue;
			if (eater.f == cerealI) {
				eater.eat();
				if (cereals[eater.s].eater == eater)
					clearEater(eater.s, eater.i);
				break;
			} else {
				eater.eat();
				break;
			}
		}
	}
	private static void actualMain() {
		init();
		for (int i = 0; i < n; i++) {
			out.println(eaten.size());
			deleteCow(i);
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
