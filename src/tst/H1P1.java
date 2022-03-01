package tst;

import java.math.BigInteger;

public class H1P1 {

	static boolean verbose = true;
	static boolean n_pp = false;
	static BigInteger factor;
	static double logSave = -1;
	static BigInteger n = new BigInteger("4");

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

		long start = System.currentTimeMillis();
		isPP();
		long end = System.currentTimeMillis();
		long result = end - start;
		System.out.println(result);

	}

}