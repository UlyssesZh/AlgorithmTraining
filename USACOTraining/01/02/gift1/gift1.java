/*
ID: ulysses4
LANG: JAVA
PROG: gift1
*/
import java.io.*;
import java.util.*;
public class gift1 {
	private static final String PROG = "gift1";
	
	private static class GiftData {
		int amount;
		int amountForEach;
		int amountRest;
		int NG;
		String[] names;
		GiftData(int amount, int NG) {
			this.amount = amount;
			if (NG == 0) {
				amountForEach = 0;
				amountRest = 0;
			} else {
				amountForEach = amount / NG;
				amountRest = amount % NG;
			}
			this.NG = NG;
			names = new String[NG];
		}
	}
	private static void actualMain() {
		int NP = scanner.nextInt();
		String[] names = new String[NP];
		for (int i = 0; i < NP; i++) names[i] = scanner.next();
		Map<String, GiftData> giftDatas = new HashMap<>(NP);
		while (scanner.hasNext()) {
			String name = scanner.next();
			GiftData giftData = new GiftData(scanner.nextInt(), scanner.nextInt());
			for (int j = 0; j < giftData.NG; j++) giftData.names[j] = scanner.next();
			giftDatas.put(name, giftData);
		}
		Map<String, Integer> amounts = new HashMap<>(NP);
		for (int i = 0; i < NP; i++) {
			String name = names[i];
			GiftData giftData = giftDatas.get(name);
			amounts.put(name, giftData.amountRest - giftData.amount);
		}
		for (int i = 0; i < NP; i++) {
			String sendName = names[i];
			GiftData giftData = giftDatas.get(sendName);
			for (int j = 0; j < giftData.NG; j++) {
				String receiveName = giftData.names[j];
				amounts.put(receiveName, amounts.get(receiveName) + giftData.amountForEach);
			}
		}
		for (int i = 0; i < NP; i++)
			out.printf("%s %d\n", names[i], amounts.get(names[i]));
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
