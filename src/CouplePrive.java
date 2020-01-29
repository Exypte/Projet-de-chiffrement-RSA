import java.math.BigInteger;

public class CouplePrive {
	private BigInteger n;
	private BigInteger u;
	
	public CouplePrive(BigInteger n, BigInteger u) {
		super();
		this.n = n;
		this.u = u;
	}

	public BigInteger getN() {
		return n;
	}

	public void setN(BigInteger n) {
		this.n = n;
	}

	public BigInteger getU() {
		return u;
	}

	public void setU(BigInteger u) {
		this.u = u;
	}
}
