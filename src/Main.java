import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chiffrement.publicKey(11);
		Chiffrement.chiffrement("bonjour", new Couple(new BigInteger("5141"), new BigInteger("7")));
	}

}
