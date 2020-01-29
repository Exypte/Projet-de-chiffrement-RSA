import java.math.BigInteger;

public class CouplePublic {
	private BigInteger n;
	private BigInteger e;
	
	public CouplePublic(BigInteger n, BigInteger e) {
		super();
		this.n = n;
		this.e = e;
	}
	
	public BigInteger getN() {
		return n;
	}
	public void setN(BigInteger n) {
		this.n = n;
	}
	public BigInteger getE() {
		return e;
	}
	public void setE(BigInteger e) {
		this.e = e;
	}
	
	
}
