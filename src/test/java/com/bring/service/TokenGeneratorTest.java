package com.bring.service;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.bring.entity.Token;


public class TokenGeneratorTest {

	@Test
	@Ignore
	public void test() {
		System.out.println(System.currentTimeMillis());
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date);
		Token token = new TokenService().generateToken();
		System.out.println(token.getTokenId());
		System.out.println(token.getCreateTime());
		System.out.println(token.getExpiryTime());
	
	}

}
