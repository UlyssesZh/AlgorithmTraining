/*
ID: ulysses4
LANG: JAVA
PROG: pprime
*/
import java.io.*;
import java.util.*;
public class pprime {
	private static final String PROG = "pprime";
	
	private static int a, b;
	private static List<Integer> ret;
	private static boolean isPrime(int p) {
		if (p < 2) return false;
		int bound = (int) Math.sqrt(p);
		for (int d = 2; d <= bound; d++) if (p % d == 0) return false;
		return true;
	}
	private static void parsePalindrome(String string) {
		int palindrome = Integer.parseInt(string);
		if (palindrome >= a && palindrome <= b && isPrime(palindrome))
			ret.add(palindrome);
	}
	private static void actualMain() {
		a = scanner.nextInt();
		b = scanner.nextInt();
		ret = new ArrayList<>();
		int digits = (int) Math.ceil(Math.log10(b) / 2);
		int bound = 1;
		for (int i = 0; i < digits; i++) bound *= 10;
		for (int p = 1; p < bound ; p += 2) {
			String string = String.valueOf(p);
			int length = string.length();
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = length - 1; i > 0; i--) {
				char charAt = string.charAt(i);
				stringBuilder.append(charAt);
			}
			StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
			stringBuilder.append(string.charAt(0));
			int zeros = (digits - length) * 2;
			StringBuilder[] stringBuilders = new StringBuilder[zeros];
			for (int i = 0; i < zeros; i++)
				stringBuilders[i] = new StringBuilder(stringBuilder);
			for (int i = 0; i < zeros; i++) {
				for (int j = 0; j <= i; j++) {
					stringBuilders[j].append('0');
				}
			}
			stringBuilder.append(string);
			parsePalindrome(stringBuilder.toString());
			stringBuilder1.append(string);
			parsePalindrome(stringBuilder1.toString());
			for (StringBuilder stringBuilder2 : stringBuilders) {
				stringBuilder2.append(string);
				parsePalindrome(stringBuilder2.toString());
			}
		}
		Collections.sort(ret);
		for (int pprime : ret) out.println(pprime);
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
