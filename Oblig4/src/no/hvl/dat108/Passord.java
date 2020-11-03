package no.hvl.dat108;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Embeddable;
import javax.xml.bind.DatatypeConverter;

@Embeddable
public class Passord {

	private String pwd_hash;
	private String pwd_salt;

	private Passord(String hash, String salt) {
		pwd_hash = hash;
		pwd_salt = salt;
	}

	public Passord() {
	}

	@Override
	public String toString() {
		return "Passord [pwd_hash=" + pwd_hash + ", pwd_salt=" + pwd_salt + "]";
	}

	public static Passord lagPassord(String passordKlartekst) {
		String salt = genererTilfeldigSalt();
		String hash = hashMedSalt(passordKlartekst, salt);

		return new Passord(hash, salt);
	}

	public String getPwd_hash() {
		return pwd_hash;
	}

	public String getPwd_salt() {
		return pwd_salt;
	}

	// Genererer tilfeldig salt
	public static String genererTilfeldigSalt() {
		SecureRandom sr;
		byte[] salt = new byte[16];
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
			sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return DatatypeConverter.printHexBinary(salt);
	}

	// Passordhash med salt
	public static String hashMedSalt(String passord, String salt) {

		char[] passchar = passord.toCharArray();
		byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);

		byte[] keyhash = null;
		try {
			PBEKeySpec pks = new PBEKeySpec(passchar, saltbytes, 1000, 256);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			keyhash = skf.generateSecret(pks).getEncoded();

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return DatatypeConverter.printHexBinary(keyhash);
	}

	// Valider med salt
	public static boolean validerMedSalt(String passord, String salt, String passordhash) {
		return passordhash.equals(hashMedSalt(passord, salt));
	}

}
