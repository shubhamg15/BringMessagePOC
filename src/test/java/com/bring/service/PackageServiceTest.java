package com.bring.service;



import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.bring.dto.ConsignmentSet;
import com.bring.exception.InvalidTrackingIdException;

public class PackageServiceTest {

	@Test
//	@Ignore
	public void test_fetch_details_with_valid_tracking_id() {
		PackageService packageService = new PackageService();
		ConsignmentSet result = packageService.fetchPackageDetails("TESTPACKAGE-AT-PICKUPPOINT");
		System.out.println(result.getConsignment().getPackageSet().getPackage().getStatusDescription());
		Assert.assertNotNull(result);
	}

	@Test
//	@Ignore
	public void test_fetch_details_with_invalid_tracking_id() {
		PackageService packageService = new PackageService();
		ConsignmentSet result = packageService.fetchPackageDetails("INVALID_ID");
		Assert.assertNull(result);
	}
	
}
