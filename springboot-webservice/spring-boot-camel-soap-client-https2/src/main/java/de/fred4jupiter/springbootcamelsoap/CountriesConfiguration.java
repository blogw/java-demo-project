package de.fred4jupiter.springbootcamelsoap;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import javax.net.ssl.SSLContext;

@Configuration
public class CountriesConfiguration {
	@Value("${client.ssl.trust-store-password}")
	private String trustStorePassword;

	@Value("${client.ssl.trust-store}")
	private Resource trustStore;

	@Value("${client.ssl.key-store-password}")
	private String keyStorePassword;

	@Value("${client.ssl.key-password}")
	private String keyPassword;

	@Value("${client.ssl.key-store}")
	private Resource keyStore;

	@Value("${soap.country.url}")
	private String soapUrl;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("de.fred4jupiter.springbootcamelsoap");
		return marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() throws Exception {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(marshaller());
		webServiceTemplate.setUnmarshaller(marshaller());
		webServiceTemplate.setDefaultUri(soapUrl);
		webServiceTemplate.setMessageSender(httpComponentsMessageSender());

		return webServiceTemplate;
	}

	@Bean
	public HttpComponentsMessageSender httpComponentsMessageSender() throws Exception {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		httpComponentsMessageSender.setHttpClient(httpClient());

		return httpComponentsMessageSender;
	}

	public HttpClient httpClient() throws Exception {
		return HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory())
				.addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor()).build();
	}

	public SSLConnectionSocketFactory sslConnectionSocketFactory() throws Exception {
		// NoopHostnameVerifier essentially turns hostname verification off as otherwise following error
		// is thrown: java.security.cert.CertificateException: No name matching localhost found
		return new SSLConnectionSocketFactory(sslContext(), NoopHostnameVerifier.INSTANCE);
	}

	public SSLContext sslContext() throws Exception {
		return SSLContextBuilder.create()
				.loadKeyMaterial(keyStore.getFile(), keyStorePassword.toCharArray(), keyPassword.toCharArray())
				.loadTrustMaterial(trustStore.getFile(), trustStorePassword.toCharArray()).build();
	}
}
