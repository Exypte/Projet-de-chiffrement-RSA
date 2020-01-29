import java.math.BigInteger;

public class CouplePublic {
	private BigInteger n;
	private BigInteger e;
	private BigInteger m;
	
	public CouplePublic(BigInteger n, BigInteger e, BigInteger m) {
		super();
		this.n = n;
		this.e = e;
		this.m = m;
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
	
	public BigInteger getM() {
		return m;
	}
	public void setM(BigInteger m) {
		this.m = m;
	}
}
