/*
ID: ulysses4
LANG: JAVA
PROG: frac1
*/
import java.io.*;
import java.util.*;
public class frac1 {
	private static final String PROG = "frac1";
	
	private static class Frac implements Comparable<Frac> {
		int nom, den;
		Frac(int a, int b) {
			nom = a;
			den = b;
		}
		@Override
		public int compareTo(Frac other) {
			return Integer.compare(nom * other.den, den * other.nom);
		}
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Frac frac = (Frac) o;
			return nom * frac.den == den * frac.nom;
		}
		@Override
		public int hashCode() {
			return Objects.hash((double) nom / den);
		}
	}
	private static void actualMain() {
		int N = scanner.nextInt();
		HashSet<Frac> fracs = new HashSet<>();
		for (int den = 1; den <= N; den++)
			for (int nom = 0; nom < den; nom++)
				fracs.add(new Frac(nom, den));
		ArrayList<Frac> fracs1 = new ArrayList<>(fracs);
		Collections.sort(fracs1);
		for (Frac frac : fracs1) {
			out.printf("%d/%d\n", frac.nom, frac.den);
		}
		out.println("1/1");
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
