package com.am53.visadirect.fundstransactions.apiext;

import com.am53.visadirect.fundstransactions.dto.ApiFilterDto;
import com.am53.visadirect.fundstransactions.exception.InternalServerErrorException;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class VisaDirectApi implements ApiGlobalData<Object, ApiFilterDto>{

    private static final Logger logger = LoggerFactory.getLogger(VisaDirectApi.class);

    private static final String URL = "https://sandbox.api.visa.com/visadirect/fundstransfer/v1/";

    private static final String username = "HX5OQRQSYA524HPHZ6Y921Bq8T31S5D8Lb7C_Qz0jVRi8UrfM";

    private static final String password = "UuN6YzByx19LFBOkpn9yu6G9Jos6lXMlAsAF";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JsonNode getResponse(String resource, List<ApiFilterDto> params, List<ApiFilterDto> query) {

        if (!resource.contains("/")) {
            resource = "/" + resource;
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL).path(resource);

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

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL).path(resource);

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

}
