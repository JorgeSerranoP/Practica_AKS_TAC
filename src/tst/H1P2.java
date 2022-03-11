package tst;

import java.math.BigInteger;
import java.util.Arrays;

public class H1P2 {

	static double logSave = -1;
	static BigInteger n;
	static boolean verbose = true;

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

		long[] array = new long[] { 5 };

		Arrays.sort(array);

		long[] array_result = new long[10];

		for (int i = 0; i < array.length; i++) {

			n = new BigInteger(Long.toString(array[i]));

			for (int j = 0; j < 10; j++) {

				long start = System.currentTimeMillis();
				calculateR();
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
