package game;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;

import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

public class Hmac {
	
	public String key;
	public String hmac;
	
	public Hmac(String computer_move) {
		key = randKey();
		hmac = hmac_sha3(computer_move);
	}
	
	private String randKey() {
		SecureRandom rnd = new SecureRandom();
		byte[] bytes = rnd.generateSeed(33);
		BigInteger key = new BigInteger(1, bytes);
		return key.toString(16);
	}
	
	private String hmac_sha3(String computer_move) {
		
		HMac hmac = new HMac(new SHA3Digest());
		byte[] resBuf=new byte[hmac.getMacSize()];
		
		hmac.init(new KeyParameter(key.getBytes()));
		byte[] textBytes = null;
		try {
			textBytes = computer_move.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		hmac.update(textBytes, 0, textBytes.length);
		hmac.doFinal(resBuf, 0);
		return new BigInteger(1, resBuf).toString(16);
	}
	
	public String getKey() {
		return key;
	}
	
	public String getHmac() {
		return hmac;
	}
}
