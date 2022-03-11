package tst;

import src.Poly;

import java.math.BigInteger;
import java.util.Arrays;

public class H2P1 {

    static double logSave = -1;
    static BigInteger n;
    static BigInteger r;
    static BigInteger factor;
    static boolean verbose = true;
    static boolean n_isprime;

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

    static BigInteger totient(BigInteger n) {
        BigInteger result = n;

        for (BigInteger i = BigInteger.valueOf(2); n.compareTo(i.multiply(i)) > 0; i = i.add(BigInteger.ONE)) {
            if (n.mod(i).compareTo(BigInteger.ZERO) == 0)
                result = result.subtract(result.divide(i));

            while (n.mod(i).compareTo(BigInteger.ZERO) == 0)
                n = n.divide(i);
        }

        if (n.compareTo(BigInteger.ONE) > 0)
            result = result.subtract(result.divide(n));

        return result;

    }

    public static boolean calculateTotient() {

        // For i = 1 to sqrt(totient)log(n) do
        // if (X+i)^n <>�X^n + i (mod X^r - 1,n), output composite;

        // sqrt(totient)log(n)
        int limit = (int) (Math.sqrt(totient(r).doubleValue()) * log());
        // X^r - 1
        Poly modPoly = new Poly(BigInteger.ONE, r.intValue()).minus(new Poly(BigInteger.ONE, 0));
        // X^n (mod X^r - 1, n)
        Poly partialOutcome = new Poly(BigInteger.ONE, 1).modPow(n, modPoly, n);
        for (int i = 1; i <= limit; i++) {
            Poly polyI = new Poly(BigInteger.valueOf(i), 0);
            // X^n + i (mod X^r - 1, n)
            Poly outcome = partialOutcome.plus(polyI);
            Poly p = new Poly(BigInteger.ONE, 1).plus(polyI).modPow(n, modPoly, n);
            if (!outcome.equals(p)) {
                if (verbose)
                    System.out.println("(x+" + i + ")^" + n + " mod (x^" + r + " - 1, " + n + ") = " + outcome);
                if (verbose)
                    System.out.println("x^" + n + " + " + i + " mod (x^" + r + " - 1, " + n + ") = " + p);
                // if (verbose) System.out.println("(x+i)^" + n + " = x^" + n + " + " + i + "
                // (mod x^" + r + " - 1, " + n + ") failed");
                factor = BigInteger.valueOf(i);
                n_isprime = false;
                return n_isprime;
            } else if (verbose)
                System.out.println(
                        "(x+" + i + ")^" + n + " = x^" + n + " + " + i + " mod (x^" + r + " - 1, " + n + ") true");
        }

        n_isprime = true;
        return n_isprime;
    }

    public static void main(String[] args) {

        long[] array = new long[] {
                7, 3147483667L, 4147483649L, 7147483727L, 1731121291L, 3731121293L, 7731121291L,
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

                r  = calculateR();
                long start = System.currentTimeMillis();
                calculateTotient();
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
