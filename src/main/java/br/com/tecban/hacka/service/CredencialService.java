package br.com.tecban.hacka.service;

import br.com.tecban.hacka.model.Token;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import br.com.tecban.hacka.utils.Constants;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Service
public class CredencialService {


    public ResponseEntity<Token> callObterCredencial() throws URISyntaxException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        // Config SSL
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);

        // Config Chamada
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("MDg4N2Y3MDctNDM5ZS00MTgwLWIzNmUtNzNlOWFjODc0NTMxOjAyZjIyZjZkLWQ0YzUtNDI2YS04YWFjLTljNTkxZTQ5NGU0YQ==");

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();


        map.add("grant_type", "client_credentials");
        map.add("scope", "accounts openid");

        String baseUrl = Constants.baseAS1;
        URI uri = new URI(baseUrl);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        ResponseEntity<Token> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Token.class);
        return response;

    }

}
