package tst;

import java.math.BigInteger;

public class H1P2 {

    static double logSave = -1;
    static BigInteger n = new BigInteger("68598349534");
    static boolean verbose = false;

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

    public static BigInteger calculateR () {

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

        long start = System.currentTimeMillis();
        calculateR();
        long end = System.currentTimeMillis();
        long result = end - start;
        System.out.println("Coste computacional: " + result);

    }
}
