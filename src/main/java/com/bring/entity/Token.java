package com.bring.entity;

import java.util.Date;

public class Token {
	private String tokenId;
	private Date createDateTime;
	private Date expiryDateTime;

	public Token(String tokenId, int timeInSeconds) {
		this.tokenId = tokenId;
		this.createDateTime = new Date();
		this.expiryDateTime = new Date(System.currentTimeMillis()+timeInSeconds*1000);
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Date getCreateTime() {
		return createDateTime;
	}

	public void setCreateTime(Date createTime) {
		this.createDateTime = createTime;
	}

	public Date getExpiryTime() {
		return expiryDateTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryDateTime = expiryTime;
	}

}
