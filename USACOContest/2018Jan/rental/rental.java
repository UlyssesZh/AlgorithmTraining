import java.io.*;
import java.util.*;
public class rental {
	private static final String PROG = "rental";
	
	private static int cowN; // # cows
	private static int shopN; // # store for milk
	private static int rentN; // # neighbor for cow
	private static int[] cows; // [N] amount of milk of cows
	private static MilkShop[] shops;
	private static int[] rents; // [R] price for cow
	private static long maxGain;
	private static class MilkShop implements Comparable<MilkShop> {
		private int quantity;
		private int price;
		private MilkShop() {
			quantity = scanner.nextInt();
			price = scanner.nextInt();
		}
		@Override
		public int compareTo(MilkShop o) {
			return Integer.compare(price, o.price);
		}
	}
	private static void init() {
		cowN = scanner.nextInt();
		shopN = scanner.nextInt();
		rentN = scanner.nextInt();
		cows = new int[cowN]; // [N] amount of milk of cows
		shops = new MilkShop[shopN];
		rents = new int[rentN];
		for (int i = 0; i < cowN; i++)
			cows[i] = scanner.nextInt();
		for (int j = 0; j < shopN; j++)
			shops[j] = new MilkShop();
		for (int j = 0; j < rentN; j++)
			rents[j] = scanner.nextInt();
		Arrays.sort(cows);
		Arrays.sort(shops);
		Arrays.sort(rents);
	}
	private static void calc() {
		int maxRent = Math.min(cowN, rentN);
		long rentGain = 0;
		long milk = 0;
		for (int i = 0; i < cowN; i++) milk += cows[i];
		for (int n = 0; n <= maxRent; n++) {
			long gain = rentGain;
			long restMilk = milk;
			for (int j = shopN - 1; j >= 0; j--)
				if (restMilk < shops[j].quantity) {
					gain += shops[j].price * restMilk;
					break;
				} else {
					gain += shops[j].price * shops[j].quantity;
					restMilk -= shops[j].quantity;
				}
			if (maxGain < gain) maxGain = gain;
			else break;
			if (n < rentN) {
				rentGain += rents[rentN - n - 1];
				milk -= cows[n];
			}
		}
	}
	private static void actualMain() {
		init();
		calc();
		out.println(maxGain);
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
