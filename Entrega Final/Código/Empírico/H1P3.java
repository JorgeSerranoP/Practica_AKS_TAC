package tst;

import java.math.BigInteger;

public class H1P3 {

	static double logSave = -1;
	static BigInteger n;
	static boolean verbose = false;
	static BigInteger n2;

	static long[] array = new long[] { 1731121291L, 3147483667L, 3731121293L, 4147483649L, 4364322029L, 4876432177L,
			6364325021L, 7147483727L, 7731121291L, 7876432127L, 16364325049L, 22264325087L, 28764321239L, 38764321237L,
			65241325021L, 68764321261L, 76121325023L, 78764321263L, 85241325023L, 98764321261L, 122121325043L,
			287643212383L, 387643212511L, 512121325043L, 556621325023L, 587643212381L, 687643212391L, 787643212379L,
			862121325071L, 987621325021L, 1258721325049L, 1756621325027L, 1876432123537L, 2222221325053L,
			3876432123497L, 4876432123499L, 5876432123491L, 8156621325031L, 8876432123491L, 9998721325039L,
			12587211325021L, 23287211325031L, 28764321234871L, 48764321234869L, 65211232872077L, 68764321234867L,
			82587211325029L, 88764321234863L, 98621232872021L, 98764321234933L, 187643212348601L, 387643212348619L,
			587643212348623L, 887643212348623L, 987643212348643L, 1876432785637831L, 3876432785637809L,
			5876432785637807L, 7876432785637883L, 8876432785637927L, 12222287643213101L, 32222287643213117L,
			52222287643213133L, 72222287643213101L, 92222287643213183L, 222222876432131059L, 422222876432131031L,
			622222876432131031L, 822222876432131021L, 922222876432131037L, 1305843009213694009L, 2305843009213693951L,
			3305843009213694011L, 5305843009213694053L, 7305843009213694067L };

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

	public static BigInteger multiplicativeOrder(BigInteger r) {
		// http://rosettacode.org/wiki/Multiplicative_order
		BigInteger k = BigInteger.ZERO;
		BigInteger result;

		do {
			k = k.add(BigInteger.ONE);
			result = n.modPow(k, r);
		} while (result.compareTo(BigInteger.ONE) != 0 && r.compareTo(k) > 0);

		if (r.compareTo(k) <= 0)
			return BigInteger.ONE.negate();
		else {
			if (verbose)
				System.out.println(n + "^" + k + " mod " + r + " = " + result);
			return k;
		}
	}

	public static BigInteger calculateR() {

		double log = log();
		double logSquared = log * log;
		BigInteger k = BigInteger.ONE;
		BigInteger r = BigInteger.ONE;
		do {
			r = r.add(BigInteger.ONE);
			if (verbose)
				System.out.println("trying r = " + r);
			k = multiplicativeOrder(r);
		} while (k.doubleValue() < logSquared);
		if (verbose)
			System.out.println("r is " + r);
		return r;
	}

	public static void main(String[] args) {

		long[] array_result = new long[10];

		for (int i = 0; i < array.length; i++) {

			n = new BigInteger(Long.toString(array[i]));
			BigInteger r = calculateR();

			for (int j = 0; j < 10; j++) {

				long start = System.nanoTime();
				for (BigInteger l = BigInteger.valueOf(2); l.compareTo(r) <= 0; l = l.add(BigInteger.ONE)) {
					BigInteger gcd = n.gcd(l);
					if (verbose)
						System.out.println("gcd(" + n + "," + i + ") = " + gcd);
				}
				long end = System.nanoTime();
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
