package com.bring.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bring.dto.ConsignmentSet;
import com.bring.exception.InvalidTrackingIdException;
import com.bring.exception.PackageAlreadyDeliveredException;

@Service
public class PackageService {

	private static final String DELIEVERED_STATUS = "delivered";

	public ConsignmentSet fetchPackageDetails(String trackingId) {
		String url = "http://sporing.bring.no/sporing.xml?q=" + trackingId;
		ConsignmentSet response = null;
		System.setProperty("proxyHost", "proxy.tcs.com"); 
		System.setProperty("proxyPort", "8080");
		try {
			response = new RestTemplate().getForObject(url,
					ConsignmentSet.class);
		} catch (Exception e) {
			return response;
		}
		return response;
	}

	public boolean isValidTrackingId(String trackingId)
			throws InvalidTrackingIdException, PackageAlreadyDeliveredException {
		ConsignmentSet consignmentSet = fetchPackageDetails(trackingId);
		if (consignmentSet == null) {
			throw new InvalidTrackingIdException("The tracking Id is invalid.");
		} else if (isPackageDelivered(consignmentSet)) {
			throw new PackageAlreadyDeliveredException("Package has been delivered. Can not generate create token.");
		} else {
			return true;
		}
	}

	private boolean isPackageDelivered(ConsignmentSet consignmentSet) {
		if(consignmentSet.getConsignment().getConsignmentId()==null){
			return false;
		}
		return consignmentSet.getConsignment().getPackageSet().getPackage()
				.getStatusDescription() == DELIEVERED_STATUS;
	}
}
