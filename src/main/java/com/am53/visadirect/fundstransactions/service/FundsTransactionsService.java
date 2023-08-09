package com.am53.visadirect.fundstransactions.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface FundsTransactionsService {

    JsonNode pullFundsTransactions(JsonNode request);

    JsonNode pushFundsTransactions(JsonNode request);

    JsonNode reverseFundsTransactions(JsonNode request);
}
