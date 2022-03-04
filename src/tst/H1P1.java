package tst;

import java.math.BigInteger;
import java.util.Arrays;

public class H1P1 {

	static boolean verbose = false;
	static boolean n_pp = false;
	static BigInteger factor;
	static double logSave = -1;
	static BigInteger n;

	public static double log() {

		if (logSave != -1)
			return logSave;

		BigInteger b;
		int temp = n.bitLength() - 1000;

		if (temp > 0) {
			b = n.shiftRight(temp);
			logSave = (Math.log(b.doubleValue()) + temp) * Math.log(2);
		} else
			logSave = (Math.log(n.doubleValue())) * Math.log(2);

		return logSave;

	}

	public static double log(BigInteger x) {

		BigInteger b;
		int temp = x.bitLength() - 1000;

		if (temp > 0) {
			b = x.shiftRight(temp);
			return (Math.log(b.doubleValue()) + temp) * Math.log(2);
		} else
			return (Math.log(x.doubleValue()) * Math.log(2));

	}

	public static boolean isPP() {

		BigInteger base = BigInteger.valueOf(2);
		BigInteger aSquared;

		do {

			BigInteger result;

			int power = Math.max((int) (log() / log(base) - 2), 1);
			int comparison;

			do {
				power++;
				result = base.pow(power);
				comparison = n.compareTo(result);
			} while (comparison > 0 && power < Integer.MAX_VALUE);

			if (comparison == 0) {
				if (verbose)
					System.out.println(n + " is a perfect power of " + base);
				factor = base;
				n_pp = true;
				return n_pp;
			}

			if (verbose)
				System.out.println(n + " is not a perfect power of " + base);

			base = base.add(BigInteger.ONE);
			aSquared = base.pow(2);

		} while (aSquared.compareTo(n) <= 0);

		if (verbose)
			System.out.println(n + " is not a perfect power of any integer less than its square root");

		return n_pp;

	}

	public static void main(String[] args) {

		long[] array = new long[] { 3147483667L, 4147483649L, 7147483727L, 1731121291L, 3731121293L, 7731121291L,
				4364322029L, 6364325021L, 7876432127L, 4876432177L, 16364325049L, 22264325087L, 65241325021L,
				85241325023L, 76121325023L, 68764321261L, 78764321263L, 98764321261L, 38764321237L, 28764321239L,
				862121325071L, 512121325043L, 122121325043L, 987621325021L, 556621325023L, 387643212511L, 287643212383L,
				787643212379L, 587643212381L, 687643212391L, 1756621325027L, 8156621325031L, 2222221325053L,
				9998721325039L, 1258721325049L, 4876432123499L, 5876432123491L, 8876432123491L, 1876432123537L,
				3876432123497L, 12587211325021L, 82587211325029L, 23287211325031L, 98621232872021L, 65211232872077L,
				88764321234863L, 48764321234869L, 28764321234871L, 98764321234933L, 68764321234867L, 987643212348643L,
				387643212348619L, 187643212348601L, 587643212348623L, 887643212348623L, 1876432785637831L,
				3876432785637809L, 5876432785637807L, 7876432785637883L, 8876432785637927L, 12222287643213101L,
				32222287643213117L, 52222287643213133L, 72222287643213101L, 92222287643213183L, 222222876432131059L,
				422222876432131031L, 622222876432131031L, 822222876432131021L, 922222876432131037L,
				1305843009213694009L, 2305843009213693951L, 3305843009213694011L, 5305843009213694053L,
				7305843009213694067L };

		Arrays.sort(array);

		long[] array_result = new long[10];

		for (int i = 0; i < array.length; i++) {

			n = new BigInteger(Long.toString(array[i]));

			for (int j = 0; j < 10; j++) {

				long start = System.currentTimeMillis();
				isPP();
				long end = System.currentTimeMillis();
				long result = end - start;
				array_result[j] = result;

			}

			System.out.println(n);

			for (int k = 0; k < 10; k++) {
				System.out.println(array_result[k]);
			}

			System.out.println("");
		}
	}
}