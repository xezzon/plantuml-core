package net.sourceforge.plantuml.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import net.sourceforge.plantuml.code.AsciiEncoder;
import net.sourceforge.plantuml.log.Logme;
import net.sourceforge.plantuml.security.SFile;

public class SignatureUtils {

	// private static byte[] salting(String pass, byte[] salt) throws
	// NoSuchAlgorithmException, InvalidKeySpecException,
	// UnsupportedEncodingException {
	// final byte[] tmp = salting2(pass, salt);
	// return SignatureUtils.getSHA512raw(tmp);
	// }

	public static synchronized byte[] salting(String pass, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		final int iterations = 500;
		final int keyLength = 512;
		final SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		final PBEKeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, iterations, keyLength);
		final SecretKey key = skf.generateSecret(spec);
		final byte[] tmp = key.getEncoded();
		return tmp;
	}


	public static String toHexString(byte data[]) {
		final StringBuilder sb = new StringBuilder(data.length * 2);
		for (byte b : data) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	public static String getMD5Hex(String s) {
		try {
			final byte[] digest = getMD5raw(s);
			assert digest.length == 16;
			return toHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			Logme.error(e);
			throw new UnsupportedOperationException(e);
		} catch (UnsupportedEncodingException e) {
			Logme.error(e);
			throw new UnsupportedOperationException(e);
		}
	}

	public static String getSHA512Hex(String s) {
		try {
			final byte[] digest = getSHA512raw(s);
			assert digest.length == 64;
			return toHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			Logme.error(e);
			throw new UnsupportedOperationException(e);
		} catch (UnsupportedEncodingException e) {
			Logme.error(e);
			throw new UnsupportedOperationException(e);
		}
	}

	public static synchronized byte[] getMD5raw(String s)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		final MessageDigest msgDigest = MessageDigest.getInstance("MD5");
		msgDigest.update(s.getBytes(UTF_8));
		return msgDigest.digest();
	}

	public static byte[] getSHA512raw(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return getSHA512raw(s.getBytes(UTF_8));
	}

	public static synchronized byte[] getSHA512raw(byte data[])
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		final MessageDigest msgDigest = MessageDigest.getInstance("SHA-512");
		msgDigest.update(data);
		return msgDigest.digest();
	}


	public static String purge(String s) {
		final String regex = "(?i)\\<img\\s+src=\"(?:[^\"]+[/\\\\])?([^/\\\\\\d.]+)\\d*(\\.\\w+)\"/\\>";
		s = s.replaceAll(regex, "<img src=\"$1$2\"/>");
		final String regex2 = "(?i)image=\"(?:[^\"]+[/\\\\])?([^/\\\\\\d.]+)\\d*(\\.\\w+)\"";
		s = s.replaceAll(regex2, "image=\"$1$2\"");
		return s;
	}

}
