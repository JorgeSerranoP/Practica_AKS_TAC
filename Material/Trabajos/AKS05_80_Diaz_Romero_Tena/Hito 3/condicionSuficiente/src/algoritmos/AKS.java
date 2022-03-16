package algoritmos;

import java.math.BigInteger;

public class AKS extends Thread {

    private BigInteger n;
    private BigInteger factor;
    private double timeelapsed;

    // Save log (n) here
    private double logSave = -1;

    public AKS(BigInteger n) {
        this.n = n;
    } 
    
    public String[] hito3Prime() {
        long start, end;
    	BigInteger r = r();
    	start = System.currentTimeMillis();
    	String primo = condicionSuficiente(r);
    	end = System.currentTimeMillis();
    	String []data = {n.toString(),String.valueOf(r), Long.toString(end-start), primo};  
    	
    	return data;  	
    }
    
    
    BigInteger totient(BigInteger n) {
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
    
 	private BigInteger r() {
		double log = this.log();
        double logSquared = log * log;
        BigInteger k = BigInteger.ONE;
        BigInteger r = BigInteger.ONE;
        do {
            r = r.add(BigInteger.ONE);
            k = multiplicativeOrder(r);
        } while (k.doubleValue() < logSquared);
		return r;
	}

    public BigInteger multiplicativeOrder(BigInteger r) {
        // TODO Consider implementing an alternative algorithm http://rosettacode.org/wiki/Multiplicative_order
        BigInteger k = BigInteger.ZERO;
        BigInteger result;

        do {
            k = k.add(BigInteger.ONE);
            result = this.n.modPow(k, r);
        }while (result.compareTo(BigInteger.ONE) != 0 && r.compareTo(k) > 0);
        if (r.compareTo(k) <= 0)
            return BigInteger.ONE.negate();
        else {
            return k;
        }
    }
      
    private double log() {
        if (logSave != -1)
            return logSave;
        BigInteger b;
        int temp = n.bitLength() - 1000;
        if (temp > 0) {
            b = n.shiftRight(temp);
            logSave = (Math.log(b.doubleValue()) / Math.log(2) + temp);
        } else
            logSave = (Math.log(n.doubleValue())) / Math.log(2);
        return logSave;
    }

    public BigInteger getFactor() {
        return factor;
    }

    public double GetElapsedTime() {
        return timeelapsed;
    }

    public String condicionSuficiente(BigInteger r) {
    	  int limit = (int) (Math.sqrt(totient(r).doubleValue()) * this.log());
          Poly modPoly = new Poly(BigInteger.ONE, r.intValue()).minus(new Poly(BigInteger.ONE, 0));
          Poly partialOutcome = new Poly(BigInteger.ONE, 1).modPow(n, modPoly, n);
          for (int i = 1; i <= limit; i++) {
              Poly polyI = new Poly(BigInteger.valueOf(i), 0);
              // X^n + i (mod X^r - 1, n)
              Poly outcome = partialOutcome.plus(polyI);
              Poly p = new Poly(BigInteger.ONE, 1).plus(polyI).modPow(n, modPoly, n);
              if (!outcome.equals(p)) return "0";
          }
          return "1";
    }
}
