import java.math.BigInteger;
import java.util.Random;

public class Chiffrement {
	public static Couple publicKey(double numBit) {
		System.out.println("publicKey");
		
		Random random = new Random();
		
		BigInteger p = BigInteger.probablePrime((int) Math.pow(2, numBit), random);
		BigInteger q = BigInteger.probablePrime((int) Math.pow(2, numBit), random);
		
		while (p.compareTo(q) == 0) {
			p = BigInteger.probablePrime((int) Math.pow(2, numBit), random);
		}
		
		BigInteger p_substract = p.subtract(new BigInteger("1"));
		BigInteger q_substract = q.subtract(new BigInteger("1"));
		
		BigInteger n = p.multiply(q);
		BigInteger m = p_substract.multiply(q_substract);
		
		BigInteger e = BigInteger.probablePrime((int) Math.pow(2, numBit / 2), random);
		
		while (m.gcd(e).compareTo(new BigInteger("1")) != 0) {
			e = BigInteger.probablePrime((int) Math.pow(2, numBit / 2), random);
		}
		
		Couple c = new Couple(n, e);
		
		System.out.println("publicKey Fin");
		
		return c;
	}
}
