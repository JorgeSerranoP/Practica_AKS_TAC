public static void calculateMCD() {
    for (BigInteger i = BigInteger.valueOf(2); i.compareTo(r) <= 0; i = i.add(BigInteger.ONE)) { // 6 OE
        BigInteger gcd = n.gcd(i); // 2 OE
        if (verbose) // 1 OE
            System.out.println("gcd(" + n + "," + i + ") = " + gcd); // 1 OE
        // 2 OE [L4-L5]
        if (gcd.compareTo(BigInteger.ONE) > 0 && gcd.compareTo(n) < 0) { // 5 OE
            factor = i; // 1 OE
            n_isprime = false; // 1 OE
            return false; // 0 OE
        }
        // 7 OE [L7-L11]
    }
}
// SOLUCIÓN: 16n + 4 --> T(n) = O1
