package com.am53.visadirect.fundstransactions.service;

import com.am53.visadirect.fundstransactions.apiext.VisaDirectApi;
import com.am53.visadirect.fundstransactions.utils.ApiResources;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FundsTransactionsServiceImpl implements FundsTransactionsService {

    private static final Logger log = LoggerFactory.getLogger(FundsTransactionsServiceImpl.class);

    private final VisaDirectApi visaDirectApi;

    @Autowired
    public FundsTransactionsServiceImpl(VisaDirectApi visaDirectApi) {
        this.visaDirectApi = visaDirectApi;
    }

    @Override
    public JsonNode pullFundsTransactions(JsonNode request) {

        log.info("Operación Pull Funds Transactions JSON: {}", request.get("localTransactionDateTime"));

        return visaDirectApi.getResponse(
                ApiResources.PULLFUNDSTRANSACTIONS.getLiteral(),
                null,
                null,
                request
        );

    }

    @Override
    public JsonNode pushFundsTransactions(JsonNode request) {
        log.info("Operación Push Funds Transactions JSON: {}", request.get("localTransactionDateTime"));

        return visaDirectApi.getResponse(
                ApiResources.PUSHFUNDSTRANSACTIONS.getLiteral(),
                null,
                null,
                request
        );
    }

    @Override
    public JsonNode reverseFundsTransactions(JsonNode request) {
        log.info("Operación Reverse Funds Transactions JSON: {}", request.get("localTransactionDateTime"));

        return visaDirectApi.getResponse(
                ApiResources.REVERSEFUNDSTRANSACTIONS.getLiteral(),
                null,
                null,
                request
        );
    }

}