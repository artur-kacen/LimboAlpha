package com.limbo.app.dao;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public final class OneWayEncryptor {

	private static final int ITERATIONS = 1024;

	public String encrypt(String plaintext, String salt) {
		MessageDigestPasswordEncoder enc = new ShaPasswordEncoder(256);
		enc.setIterations(ITERATIONS);
		String hashedPass = enc.encodePassword(plaintext, salt);
		return hashedPass;
	}
}