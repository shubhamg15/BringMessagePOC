package com.bring.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bring.entity.Token;

@Service
public class TokenService {

	private static final int TOKEN_EXPIRY_SECONDS = 360000;

	public Token generateToken() {

		return new Token(UUID.randomUUID().toString().replaceAll("-", ""),
				TOKEN_EXPIRY_SECONDS);
	}

	public boolean isValidCreateToken(String tokenId) {

		return false;
	
	}

}
