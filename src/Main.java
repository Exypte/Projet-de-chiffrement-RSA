import java.math.BigInteger;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CouplePublic cp = Chiffrement.publicKey(11);
		CouplePrive cpp = Chiffrement.privateKey(cp);
		ArrayList<BigInteger> m = Chiffrement.chiffrement("Bonjour, je m'appel Henry !", cp);
		Chiffrement.dechiffrement(m, cpp);
		String json = new Gson().toJson(m);
		System.out.println(json);
		ArrayList<BigInteger> t = new Gson().fromJson(json, new TypeToken<ArrayList<BigInteger>>(){}.getType());
		Chiffrement.dechiffrement(t, cpp);
	}
}
