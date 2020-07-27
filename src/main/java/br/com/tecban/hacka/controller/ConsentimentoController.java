package br.com.tecban.hacka.controller;

import br.com.tecban.hacka.model.Token;
import br.com.tecban.hacka.service.ConsentimentoService;
import br.com.tecban.hacka.service.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@RestController("/v1")
public class ConsentimentoController {

    @Autowired
    CredencialService credencialService;

    @Autowired
    ConsentimentoService consentimentoService;


    @PostMapping(value = "/consentimento")
    public ResponseEntity<Token> criarConsentimento() throws URISyntaxException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        ResponseEntity<Token> token = credencialService.callObterCredencial();

        ResponseEntity<Token> response = consentimentoService.obterConsentimento(token);

        return response;
    }

}
