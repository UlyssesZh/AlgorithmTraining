/*
ID: ulysses4
LANG: JAVA
PROG: friday
*/
import java.io.*;
import java.util.*;
public class friday {
	private static final String PROG = "friday";
	
	private static void actualMain(String[] args) throws IOException {
		int years = scanner.nextInt();
		int[] daysCount = new int[7];
		int dayCount = 13;
		for (int i = 1900; i < 1900 + years; i++) {
			for (int j = 0; j < 12; j++) {
				daysCount[dayCount % 7]++;
				switch (j) {
					case 0:
					case 2:
					case 4:
					case 6:
					case 7:
					case 9:
					case 11:
						dayCount += 31;
						break;
					case 3:
					case 5:
					case 8:
					case 10:
						dayCount += 30;
						break;
					case 1:
						dayCount += (i % 100 == 0 ? (i % 400 == 0) : (i % 4 == 0)) ? 29 : 28;
						break;
				}
			}
		}
		for (int k = 6; k < 6 + 7; k++) {
			out.print(daysCount[k % 7]);
			out.print(k == 6 + 7 - 1 ? '\n' : ' ');
		}
	}
	
	private static InputStream in;
	private static PrintStream out;
	private static Scanner scanner;
	public static void main(String[] args) throws IOException {
		in = new FileInputStream(PROG + ".in");
		out = new PrintStream(PROG + ".out");
		scanner = new Scanner(in);
		actualMain(args);
		in.close();
		out.close();
		scanner.close();
	}
}

