import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CouplePublic cp = Chiffrement.publicKey(11);
		CouplePrive cpp = Chiffrement.privateKey(cp);
		Chiffrement.dechiffrement(Chiffrement.chiffrement("Bonjour, je m'appel Henry !", cp), cpp);
	}
}
