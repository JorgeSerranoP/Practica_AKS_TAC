public static BigInteger totient(BigInteger n) {
    BigInteger result = n; // 1 OE
    for (BigInteger i = BigInteger.valueOf(2); n.compareTo(i.multiply(i)) > 0; i = i.add(BigInteger.ONE)) { // 8 OE
        if (n.mod(i).compareTo(BigInteger.ZERO) == 0) // 4 OE
            result = result.subtract(result.divide(i)); // 3 OE
        // 7 OE [L4 - L5]
        while (n.mod(i).compareTo(BigInteger.ZERO) == 0) // 4 OE
            n = n.divide(i); // 2 OE
        // 6n + 4  OE [L7 - L8] 
    }
    // 6n^2 + 17n + 3 OE [L3 - L10]
    if (n.compareTo(BigInteger.ONE) > 0) // 2 OE
        result = result.subtract(result.divide(n)); // 2 OE
    // 4 OE [L12 - L13]
    return result; // 0 OE
}
//SOLUCIÓN: 6n^2 + 17n + 8 OE --> T(n) = O2