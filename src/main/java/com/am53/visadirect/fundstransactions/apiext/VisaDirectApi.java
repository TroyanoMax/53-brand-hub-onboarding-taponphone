package com.am53.visadirect.fundstransactions.apiext;

import com.am53.visadirect.fundstransactions.dto.ApiFilterDto;
import com.am53.visadirect.fundstransactions.exception.InternalServerErrorException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.List;

@Component
public class VisaDirectApi implements ApiGlobalData<Object, ApiFilterDto>{

    private static final Logger logger = LoggerFactory.getLogger(VisaDirectApi.class);

    @Value("${visa.direct.url-base}")
    private String url;

    @Value("${visa.direct.url-funds}")
    private String urlFunds;

    @Value("${visa.direct.url-reverse-funds}")
    private String urlReverseFunds;

    @Value("${visa.direct.url-foreign-exchage}")
    private String urlForeignExchange;

    @Value("${visa.direct.url-paai-funds-transf}")
    private String urlPaaiFundsTransf;

    @Value("${visa.direct.apiKey}")
    private String username;

    @Value("${visa.direct.apiSecret}")
    private String password;

    @Value("${server.ssl.key-store}")
    private String keyStorePath;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    @Value("${server.ssl.key-store-type}")
    private String keyStoreType;

    @Value("${value.algorithm}")
    private String algorithm;

    @Value("${value.protocol}")
    private String protocol;

    private final RestTemplate restTemplate;

    @Autowired
    public VisaDirectApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public JsonNode getResponse(String resource, List<ApiFilterDto> params, List<ApiFilterDto> query) {

        if (!resource.contains("/")) {
            resource = "/" + resource;
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url).path(resource);

        if (params != null && !params.isEmpty()) {
            params.forEach(p -> uriBuilder.queryParam(p.getKey(), p.getValue()));
        }

        if (query != null && !query.isEmpty()) {
            StringBuilder uriQuery = new StringBuilder("q=");
            query.forEach(q -> {
                String expresion = q.getKey() + q.getOperation() + q.getValue() + q.getUnion();
                uriQuery.append(expresion);
            });

            uriBuilder.query(uriQuery.toString());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<JsonNode> response;

        logger.info("URL: {}", uriBuilder.build().toUri());

        try {
            response = restTemplate.exchange(
                            uriBuilder.build().toUri(),
                            HttpMethod.GET,
                            entity,
                            JsonNode.class
                        );
        } catch (RestClientException e) {
            throw new InternalServerErrorException("Exception occurred while getting Collection to: " + resource + " due to: " + e.getMessage());
        }

        return response.getBody();

    }

    @Override
    public JsonNode getResponse(String resource, List<ApiFilterDto> params, List<ApiFilterDto> query, Object body) {

        if (!resource.contains("/")) {
            resource = "/" + resource;
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url).path(resource);

        if (params != null && !params.isEmpty()) {
            params.forEach(p -> uriBuilder.queryParam(p.getKey(), p.getValue()));
        }

        if (query != null && !query.isEmpty()) {
            StringBuilder uriQuery = new StringBuilder("q=");
            query.forEach(q -> {
                String expresion = q.getKey() + q.getOperation() + q.getValue() + q.getUnion();
                uriQuery.append(expresion);
            });

            uriBuilder.query(uriQuery.toString());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        ResponseEntity<JsonNode> response;

        logger.info("URL: {}", uriBuilder.build().toUri());

        try {
            response = restTemplate.exchange(
                    uriBuilder.build().toUri(),
                    HttpMethod.POST,
                    entity,
                    JsonNode.class
            );
        } catch (RestClientException e) {
            throw new InternalServerErrorException("Exception occurred while getting Collection to: " + resource + " due to: " + e.getMessage());
        }

        return response.getBody();

    }

    @Override
    public JsonNode getFundsPostResponse(String resource, Object request) {

        try {

            logger.info("START Sample Code for Two-Way (Mutual) SSL");
            URL apiUrl = new URL(this.url+this.urlFunds+resource);

            // THIS IS EXAMPLE ONLY how will cert and key look like
            // keystorePath = "visa.jks"
            // keystorePassword = "password"
            // Make a KeyStore from the PKCS-12 file
            KeyStore ks = KeyStore.getInstance(keyStoreType);
            try (FileInputStream fis = new FileInputStream(keyStorePath)) {
                ks.load(fis, keyStorePassword.toCharArray());
            }

            // Make a KeyManagerFactory from the KeyStore
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
            kmf.init(ks, keyStorePassword.toCharArray());

            // Now make an SSL Context with our Key Manager and the default Trust Manager
            SSLContext sslContext = SSLContext.getInstance(protocol);
            sslContext.init(kmf.getKeyManagers(), null, null);

            HttpURLConnection con = (HttpURLConnection) apiUrl.openConnection();
            ((HttpsURLConnection)con).setSSLSocketFactory(sslContext.getSocketFactory());

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            con.setRequestProperty("Authorization", authHeaderValue);

            String jsonBody = request.toString();

            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int status = con.getResponseCode();
            logger.info("Http Status: {}", status);

            BufferedReader in;
            if (status == 200) {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                logger.info("Two-Way (Mutual) SSL test failed");
            }

            String response;
            StringBuilder content = new StringBuilder();
            while ((response = in.readLine()) != null) {
                content.append(response);
            }

            in.close();
            con.disconnect();

            String infoContent = content.toString();

            logger.info(infoContent);
            logger.info("END Sample Code for Two-Way (Mutual) SSL");

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readTree(content.toString());

        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException |
                 UnrecoverableKeyException e) {
            logger.info("Ha ocurrido un error: {}", e.getMessage());
        } catch (CertificateException e) {
            logger.info("Ha ocurrido un error con el certificado: {}", e.getMessage());
        }

        return null;
    }
}
