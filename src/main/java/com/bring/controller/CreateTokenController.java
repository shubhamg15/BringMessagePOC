package com.bring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bring.dao.TokenManagerDao;
import com.bring.entity.Shipment;
import com.bring.entity.Token;
import com.bring.service.PackageService;
import com.bring.service.TokenService;

@Controller
public class CreateTokenController {

	@Autowired
	private PackageService packageService;
	
	@Autowired
	private TokenManagerDao tokenManagerDao;
	
	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/createToken", method = RequestMethod.GET)
	public @ResponseBody
	String uploadFileHandler(@RequestParam("shipmentId") String shipmentId) {
		Token createDataToken = null;
		if (shipmentId == null) {
			return "Invalid Request! Can not generate the token.";
		}
		try {
			if (packageService.isValidTrackingId(shipmentId)) {
				createDataToken = tokenService.generateToken();
				tokenManagerDao.saveShipment(new Shipment(shipmentId, createDataToken));
			}
		} catch (Exception e) {
			return "Failed to create token " + shipmentId + " => "
					+ e.getMessage();
		}
		return "http://localhost:8080/BringUploadData/uploadFile/"+createDataToken.getTokenId();
	}
}