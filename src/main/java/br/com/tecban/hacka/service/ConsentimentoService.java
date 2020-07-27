package br.com.tecban.hacka.service;

import br.com.tecban.hacka.model.Token;
import br.com.tecban.hacka.utils.Constants;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ConsentimentoService {

    public ResponseEntity<Token> obterConsentimento(ResponseEntity<Token> token) throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token.getBody().getAccess_token());

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();


        map.add("grant_type", "client_credentials");
        map.add("scope", "accounts openid");

        String baseUrl = Constants.baseAS1;
        URI uri = new URI(baseUrl + "/open-banking/v3.1/aisp/account-access-consents");

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        ResponseEntity<Token> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Token.class);
        return response;

    }

}
