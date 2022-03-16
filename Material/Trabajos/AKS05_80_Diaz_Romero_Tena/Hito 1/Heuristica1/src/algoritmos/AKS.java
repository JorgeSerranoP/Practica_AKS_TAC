package algoritmos;
import java.math.BigInteger;

/**
 * @author Vincent
 *
 * Bugs fixed and modified for educational purposes @uc3m
 */

public class AKS extends Thread {

    private BigInteger n;
    private boolean n_isprime = true;
    private BigInteger factor;

    private double logSave = -1;

    public AKS(BigInteger n) {
        this.n = n;
    }

    public String[] isPrime() {
    	
    	 // Almacenamos los datos dentro del array
        String []data = {n.toString(),"0","0","0","0"};  
        long start, end;
        
    	// HEURISTICA 1

    	start = System.currentTimeMillis();
    	h1();
        end = System.currentTimeMillis();
        
        data[1] = Long.toString(end-start);
        
        if( n_isprime == false) return data;
        
        // HEURISTICA 2
        
// ------------- Calculo de r -----------------
        start = System.currentTimeMillis();
        BigInteger r = r();
        end = System.currentTimeMillis();
        
        data[2] = Long.toString(end-start);
        
//-------------- MCD ---------------------------
        start = System.currentTimeMillis();
        mcd(r);
        end = System.currentTimeMillis();
        
        data[3] = Long.toString(end - start);
 
        if(n_isprime==true) {
        	data[4]="1";	
        }else {
        	data[4]="0";
        }
        
        return data;
    }
        
	private void mcd(BigInteger r) {
		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(r) <= 0; i = i.add(BigInteger.ONE)) {
            BigInteger gcd = n.gcd(i);
            if (gcd.compareTo(BigInteger.ONE) > 0 && gcd.compareTo(n) < 0) {
                factor = i;
                n_isprime = false;
                return;
            }
        }
        if (n.compareTo(r) <= 0) {
            n_isprime = true;
        }
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

	private void h1() {
		BigInteger base = BigInteger.valueOf(2);
        BigInteger aSquared;

        do {
            BigInteger result;
            int power = Math.max((int) (log() / log(base) - 2), 1);
            int comparison;

            do {
                power++;
                result = base.pow(power);
                comparison = n.compareTo(result);
            }while (comparison > 0 && power < Integer.MAX_VALUE);

            if (comparison == 0) {
                factor = base;
                n_isprime = false;
                return;
            }
            base = base.add(BigInteger.ONE);
            aSquared = base.pow(2);
        } while (aSquared.compareTo(this.n) <= 0);
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

    private double log(BigInteger x) {
        // from http://world.std.com/~reinhold/BigNumCalcSource/BigNumCalc.java
        BigInteger b;

        int temp = x.bitLength() - 1000;
        if (temp > 0) {
            b = x.shiftRight(temp);
            return (Math.log(b.doubleValue()) / Math.log(2.0D) + temp);
        } else
            return (Math.log(x.doubleValue()) / Math.log(2.0D));
    }

    public BigInteger getFactor() {
        return factor;
    }   

}
