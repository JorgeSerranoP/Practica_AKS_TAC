package tst;

import java.math.BigInteger;

import src.AKS;

public class H2P2 {

	static BigInteger n;

	public static void main(String[] args) {

		long[] array = new long[] { 1, 2, 3, 5, 7, 31, 53, 79, 89, 97, 127, 281, 313, 619, 811, 1279, 3877, 5059, 6977,
				8807, 10007, 11273, 23371, 37871, 44221, 55103, 69877, 75079, 81547, 91019, 99991, 100003, 110017,
				220009, 340477, 476467, 526459, 636469, 719483, 839459, 949471, 983461, 1000003, 1134541, 2134549,
				3334531, 4534549, 5554537, 6554531, 7154549, 8454533, 9399563, 9999533, 10000019, 17995319, 22895309,
				36895343, 45895307, 57895309, 61895311, 72695303, 88195307, 91195331, 99195347, 100000007, 111953027,
				221953031, 336953033, 442453021, 551324903, 662524937, 772524959, 882524933, 922524923, 999999929 };

		long[] array_result = new long[10];

		for (int i = 0; i < array.length; i++) {

			n = new BigInteger(Long.toString(array[i]));

			AKS a = new AKS(n);

			boolean x = false;

			for (int j = 0; j < 10; j++) {

				long start = System.currentTimeMillis();
				x = a.isPrime();
				long end = System.currentTimeMillis();
				long result = end - start;
				array_result[j] = result;

			}

			System.out.println(n);
			System.out.println(x);

			for (int k = 0; k < 10; k++) {
				System.out.println(array_result[k]);
			}

			System.out.println("");
		}
	}
}
