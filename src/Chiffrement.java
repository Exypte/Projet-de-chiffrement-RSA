import java.math.BigInteger;
import java.util.ArrayList;
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
	
	public static void privateKey() {
		ArrayList<BigInteger> r = new ArrayList<>();
		ArrayList<BigInteger> u = new ArrayList<>();
		ArrayList<BigInteger> v = new ArrayList<>();
		r.add(new BigInteger("7"));
		r.add(new BigInteger("4992"));
		u.add(new BigInteger("0"));
		u.add(new BigInteger("1"));
		v.add(new BigInteger("1"));
		v.add(new BigInteger("0"));
		while (r.get(r.size()-1).compareTo(new BigInteger("0"))!=0) {
			BigInteger ri = r.get(r.size()-1);
			BigInteger rim1 = r.get(r.size()-2);
			BigInteger ui = u.get(r.size()-1);
			BigInteger uim1 = u.get(r.size()-2);
			BigInteger vi = v.get(r.size()-1);
			BigInteger vim1 = v.get(r.size()-2);
			r.add(rim1.subtract(rim1.divide(ri).multiply(ri)));
			u.add(uim1.subtract(rim1.divide(ri).multiply(ui)));
			v.add(vim1.subtract(rim1.divide(ri).multiply(vi)));
		}
		BigInteger ufinal = u.get(u.size()-2);
		
		
	}
	
	public static void chiffrement(String message, Couple couple) {
		ArrayList<Integer> ascii = new ArrayList<Integer>();
		
		for(char ch : message.toCharArray()) {
			ascii.add((int)ch);
		}
		
		System.out.println(ascii.toString());
	}
}
