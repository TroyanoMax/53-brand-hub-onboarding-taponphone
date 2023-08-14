package com.am53.visadirect.fundstransactions.service;

import com.am53.visadirect.fundstransactions.apiext.VisaDirectApi;
import com.am53.visadirect.fundstransactions.utils.ApiResources;
import com.am53.visadirect.fundstransactions.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

        // log message
        log.info("Json Request: {}", request);

        return visaDirectApi.getFundsPostResponse(
                ApiResources.PULLFUNDSTRANSACTIONS.getLiteral(),
                request
        );

    }

    @Override
    public JsonNode pushFundsTransactions(JsonNode request) {
        log.info("Operación Push Funds Transactions JSON: {}", request.get("localTransactionDateTime"));

        return visaDirectApi.getFundsPostResponse(
                ApiResources.PUSHFUNDSTRANSACTIONS.getLiteral(),
                request
        );
    }

    @Override
    public JsonNode reverseFundsTransactions(JsonNode request) {
        log.info("Operación Reverse Funds Transactions JSON: {}", request.get("localTransactionDateTime"));

        return visaDirectApi.getFundsPostResponse(
                ApiResources.REVERSEFUNDSTRANSACTIONS.getLiteral(),
                request
        );
    }

}