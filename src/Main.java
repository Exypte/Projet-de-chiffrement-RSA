import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chiffrement.dechiffrement(Chiffrement.chiffrement("Bonjour thierry la petite ... !", new CouplePublic(new BigInteger("5141"), new BigInteger("7"))), new CouplePrive(new BigInteger("5141"), new BigInteger("4279")));
	}
}
