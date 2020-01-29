import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Chiffrement {
	
	public static CouplePublic publicKey(double numBit) {
		
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
		
		CouplePublic c = new CouplePublic(n, e, m);
		
		return c;
	}
	
	public static CouplePrive privateKey(CouplePublic couple) {

		ArrayList<BigInteger> r = new ArrayList<>();
		ArrayList<BigInteger> u = new ArrayList<>();
		ArrayList<BigInteger> v = new ArrayList<>();
		r.add(couple.getE());
		r.add(couple.getM());
		u.add(new BigInteger("1"));
		u.add(new BigInteger("0"));
		v.add(new BigInteger("0"));
		v.add(new BigInteger("1"));
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
		
		BigInteger m = couple.getM();
		BigInteger ufinal = u.get(u.size()-2);
		BigInteger k = new BigInteger("-1");
		
		while (!(new BigInteger("2").compareTo(ufinal)==-1 && ufinal.compareTo(m) == -1)){
			ufinal = ufinal.subtract(k.multiply(m));
			k = k.subtract(new BigInteger("1"));
		}

		return new CouplePrive(couple.getN(), ufinal);
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
