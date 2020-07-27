package br.com.tecban.hacka.controller;

import br.com.tecban.hacka.model.Token;
import br.com.tecban.hacka.service.ContaService;
import br.com.tecban.hacka.service.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@RestController("/v1")
public class ContaController {

    @Autowired
    CredencialService credencialService;

    @Autowired
    ContaService contaService;

    @GetMapping(value = "/contas")
    public ResponseEntity<String> getAllContas() throws URISyntaxException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        ResponseEntity<Token> token = credencialService.callObterCredencial();

        ResponseEntity<String> response = contaService.getAll(token);

        return response;
    }
}
