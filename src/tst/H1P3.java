package tst;

import java.math.BigInteger;

public class H1P3 {

    static BigInteger n1 = new BigInteger("13");
    static BigInteger n2 = new BigInteger("68598349534");

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        n1.gcd(n2);
        System.out.println("El mcd de " + n1 +  " y " + n2 + " es: " + n1.gcd(n2));
        long end = System.currentTimeMillis();
        long result = end - start;
        System.out.println("Coste computacional: " + result);

    }
}
