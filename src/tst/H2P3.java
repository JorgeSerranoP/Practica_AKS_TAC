//package tst;
//
//import java.math.BigInteger;
//
//public class H2P3 {
//
//	public static boolean isPP() {
//
//		do {
//			
//			BigInteger result;
//			// 0
//
//			int power = Math.max((int) (log() / log(base) - 2), 1); 
//			// 1 + 6 = la igualdad es 1, la funcion es 1, el log 1 
//			// el otro log 1, la div es 1, el menos es 1 y el int 1
//			
//			// =
//			// 7 // !
//			
//			int comparison; // 0
//
//			do {
//				power++; 
//				// 2
//				result = base.pow(power); 
//				// 1 + 1 = 2
//				comparison = n.compareTo(result); 
//				// 1 + 1 = 2
//			} while (comparison > 0 && power < Integer.MAX_VALUE); // 1 + 2 = 3
//			
//			// 6 + (3  + n * (6 + 3)) --> DO + WHILE
//			// =
//			// 9 + 9n // !
//
//			if (comparison == 0) { // 1
//				if (verbose) // 1
//					System.out.println(n + " is a perfect power of " + base); 
//					// 1
//				// 1 + 1
//				// =
//				// 2
//				
//				factor = base; 
//				// 1
//				n_isprime = false; 
//				// 1
//				return n_isprime; 
//				// 0
//			
//				// 2 + 1 +1
//				// =
//				// 4 //
//			}
//			
//			// 1 + 4
//			// =
//			// 5 // !
//
//			if (verbose) // 1
//				System.out.println(n + " is not a perfect power of " + base); 
//				// 1
//			
//			// 1 + 1
//			// =
//			// 2 // !
//
//			base = base.add(BigInteger.ONE); 
//			// 1 + 1 = 2
//			aSquared = base.pow(2); 
//			// 1 + 1 = 2
//			
//			// =
//			// 4 // !
//			
//			
//			
//			// (7) + (9 + 9n) + (5) + (2) + (4)
//			// =
//			// 27 + 9n
//			
//		} while (aSquared.compareTo(this.n) <= 0); // 2 + 1 = 3
//		
//		// 27 + 9n + (3 + n * (27 + 9n + 3)) --> DO + WHILE
//		// = 
//		// 27 + 9n + (3 + 30n + 9n^2)
//		// =
//		// 9n^2 + 39n + 30 // !!
//		
//		if (verbose) // 1
//			System.out.println(n + " is not a perfect power of any integer less than its square root"); // 1
//		
//		// =
//		// 2 !!
//		
//		
//		
//		// =
//		// 9n^2 + 39n + 30 + 2
//		// 9n^2 + 39n + 32
//		// =
//		// 9n^2 + 39n + 32
//		
//	}
//		// SOLUCIÓN = 9n^2 + 39n + 32 !!! Xn^2 + Xn + C = T(n) = O2
//	
//	public static BigInteger calculateR() {
//
//		double log = log(); // 1 + 1
//		double logSquared = log * log; // 1 + 1
//		BigInteger k = BigInteger.ONE; // 1 + 1
//		BigInteger r = BigInteger.ONE; // 1 + 1
//		
//		// =
//		// 8 !
//		
//		do {
//			r = r.add(BigInteger.ONE); // 1 + 2
//			if (verbose) // 1
//				System.out.println("trying r = " + r); // 1
//			// = 
//			// 2
//			k = multiplicativeOrder(r); // 1 + 1 !fct()
//			
//			// = 
//			// 7
//		} while (k.doubleValue() < logSquared); // 1 + 1
//		
//		// DO + WHILE -- C + n * (S + C)
//		// 7 + (2 + n * (7 + 2)
//		// =
//		// 7 + 2 + 9n
//		// =
//		// 9n + 9 !
//		
//		
//		if (verbose) // 1
//			System.out.println("r is " + r); // 1
//		
//		// =
//		// 2
//		
//		return r; // 0
//		
//		// 9n + 19 !
//	}
//	
//	// SOLUCIÓN = 9n + 19 ; T(n) = O1
//	
//	
//	
//	for (BigInteger i = BigInteger.valueOf(2); i.compareTo(r) <= 0; i = i.add(BigInteger.ONE)) { // 1+1 ; 2 ; 1+2 
//		BigInteger gcd = n.gcd(i); 
//		// 2
//		if (verbose) // 1
//			System.out.println("gcd(" + n + "," + i + ") = " + gcd); // 1
//		// =
//		// 2
//		if (gcd.compareTo(BigInteger.ONE) > 0 && gcd.compareTo(n) < 0) { // 5
//			factor = i; // 1
//			n_isprime = false; // 1
//			return false; // 0
//		}
//		// =
//		// 7
//		
//		// =
//		// 11
//		
//	}
//	
//	// C + n * (S + C)
//	// =
//	// 2 + (2 + n * (11 + 3 + 2))
//	// 2 + (2 + 16n)
//	
//	// SOLUCIÓN = 16n + 4 ; T(n) = O1
//	
//	
//	
//	
//	
//}
