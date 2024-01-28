package com.app.util;

import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
@Component
public class PasswordGeneratorUtil {

	public String passwordGenerator() {
		char[] possibleCharacters = (new String(
				"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?"))
				.toCharArray();
		int randomStrLength = 10;
		String randomStr = RandomStringUtils.random(randomStrLength, 0, possibleCharacters.length - 1, false, false,
				possibleCharacters, new SecureRandom());
		return randomStr;

	}
}
