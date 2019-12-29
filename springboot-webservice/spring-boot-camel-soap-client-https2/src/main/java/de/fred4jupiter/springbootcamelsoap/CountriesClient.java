package de.fred4jupiter.springbootcamelsoap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Component
public class CountriesClient {

	private static final Logger log = LoggerFactory.getLogger(CountriesClient.class);

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	public GetCountryResponse getCountry(String countryName) {
		GetCountryRequest request = new GetCountryRequest();
		request.setName(countryName);

		log.info("Requesting country {}", countryName);

		GetCountryResponse response = (GetCountryResponse) webServiceTemplate.marshalSendAndReceive(request,
				new SoapActionCallback("getCountry"));
		return response;
	}

}