package com.am53.visadirect.fundstransactions.service;

import com.am53.visadirect.fundstransactions.apiext.VisaDirectApi;
import com.am53.visadirect.fundstransactions.dto.ApiFilterDto;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.core.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FundsTransactionsServiceImpl implements FundsTransactionsService {

    private static final Logger log = LoggerFactory.getLogger(FundsTransactionsServiceImpl.class);

    @Autowired
    private VisaDirectApi visaDirectApi;

    @Override
    public JsonNode pullFundsTransactions(JsonNode request) {

        log.info("Operación Pull Funds Transactions JSON: {}", request.get("localTransactionDateTime"));

        return visaDirectApi.getResponse(
                "pullfundstransactions",
                null,
                null,
                request
        );

    }

    @Override
    public JsonNode pushFundsTransactions(JsonNode request) {
        log.info("Operación Push Funds Transactions JSON: {}", request.get("localTransactionDateTime"));

        return visaDirectApi.getResponse(
                "pushfundstransactions",
                null,
                null,
                request
        );
    }

    @Override
    public JsonNode reverseFundsTransactions(JsonNode request) {
        log.info("Operación Reverse Funds Transactions JSON: {}", request.get("localTransactionDateTime"));

        return visaDirectApi.getResponse(
                "reversefundstransactions",
                null,
                null,
                request
        );
    }

}