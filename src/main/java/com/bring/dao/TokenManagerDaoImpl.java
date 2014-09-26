package com.bring.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bring.entity.Shipment;
import com.bring.entity.Token;

@Repository
public class TokenManagerDaoImpl implements
		TokenManagerDao {

	@Autowired
	private DataSource dataSource;

	private static final String INSERT_TOKEN_SQL = "INSERT INTO TOKEN (token_id, creation_datetime, expiry_datetime) VALUES (?, ?, ?)";
	private static final String INSERT_SHIPMENT_SQL = "INSERT INTO SHIPMENT (shipment_number, createdata_token_id, showdata_token_id) VALUES (?, ?, ?)";

	public void saveShipment(Shipment shipment) {
		Token createDataToken = shipment.getCreateDataToken();
		new JdbcTemplate(dataSource).update(
				INSERT_TOKEN_SQL,
				new Object[] { createDataToken.getTokenId(),
						createDataToken.getCreateTime(),
						createDataToken.getExpiryTime() });
		new JdbcTemplate(dataSource).update(
				INSERT_SHIPMENT_SQL,
				new Object[] { shipment.getShipmentId(),
						shipment.getCreateDataToken().getTokenId(),""});
		
	}
}
