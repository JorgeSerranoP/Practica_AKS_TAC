public static BigInteger calculateR() {
	double log = log(); // 2 OE
	double logSquared = log * log; // 2 OE
	BigInteger k = BigInteger.ONE; // 2 OE
	BigInteger r = BigInteger.ONE; // 2 OE
	do {
		r = r.add(BigInteger.ONE); // 3 OE
		if (verbose) // 1 OE
			System.out.println("trying r = " + r); // 1 OE
		// 2 OE [L8-L9]
		k = multiplicativeOrder(r); // 2 OE
	} while (k.doubleValue() < logSquared); // 2 OE
	// 9n + 9 OE [L6-L12]
	if (verbose) // 1 OE
		System.out.println("r is " + r); // 1 OE
	// 2 OE [L14-L15]
	return r; // 0 OE
}
// SOLUCIÓN: 9n + 19 --> T(n) = O1
