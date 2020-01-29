import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Chiffrement {
	public static CouplePublic publicKey(double numBit) {
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
		
		CouplePublic c = new CouplePublic(n, e);
		
		System.out.println("publicKey Fin");
		
		return c;
	}
	
	public static ArrayList<BigInteger> chiffrement(String message, CouplePublic couple) {
		ArrayList<BigInteger> ascii = new ArrayList<BigInteger>();
		
		for(char ch : message.toCharArray()) {
			int ascii_code = (int) ch;
			ascii.add(new BigInteger(Integer.toString(ascii_code)).modPow(couple.getE(), couple.getN()));
		}
		
		return ascii;
	}
	
	public static String dechiffrement(ArrayList<BigInteger> message, CouplePrive couple) {
		
		String str = new String();
		
		for(int i = 0; i < message.size(); i++) {
			str += (char) message.get(i).modPow(couple.getU(), couple.getN()).intValue();
		}
		
		System.out.println(str);
		
		return str;
	}
}
