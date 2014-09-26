package com.bring.entity;

public class Shipment {

	private String shipmentId;
	private Token createDataToken;
	private Token showDataToken;

	public Shipment(String shipmentId, Token createDataToken){
		this.shipmentId = shipmentId;
		this.createDataToken = createDataToken;
	}
	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Token getCreateDataToken() {
		return createDataToken;
	}

	public void setCreateDataToken(Token createDataToken) {
		this.createDataToken = createDataToken;
	}

	public Token getShowDataToken() {
		return showDataToken;
	}

	public void setShowDataToken(Token showDataToken) {
		this.showDataToken = showDataToken;
	}

}
