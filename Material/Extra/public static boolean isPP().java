public static boolean isPP() {
	do {
		BigInteger result; // 0 OE
		int power = Math.max((int) (log() / log(base) - 2), 1); // 7 OE
		int comparison; // 0 OE
		do {
			power++; // 2 OE
			result = base.pow(power); // 2 OE
			comparison = n.compareTo(result); // 2 OE
		} while (comparison > 0 && power < Integer.MAX_VALUE); // 3 OE
		// 9 + 9n OE [L6-L10]
		if (comparison == 0) { // 1 OE
			if (verbose) // 1 OE
				System.out.println(n + " is a perfect power of " + base); // 1 OE
			// 2 OE [L13-L14]
			factor = base; // 1OE
			n_isprime = false; // 1OE
			return n_isprime; // 0OE
		}
		// 5 OE [L12-L19]
		if (verbose) // 1OE
			System.out.println(n + " is not a perfect power of " + base); // 1OE
		// 2 OE [L21-L22]
		base = base.add(BigInteger.ONE); // 2OE
		aSquared = base.pow(2); // 2OE		
	} while (aSquared.compareTo(this.n) <= 0); // 3OE
	// 9n^2 + 39n + 30 OE [L2-L26]
	if (verbose) // 1 OE
		System.out.println(n + " is not a perfect power of any integer less than its square root"); // 1 OE
	// 2 OE [L28-L29]	
}
// SOLUCIÓN: 9n^2 + 39n + 32 OE --> T(n) = O2
