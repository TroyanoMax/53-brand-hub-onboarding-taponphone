package com.am53.visadirect.fundstransactions.controller;

import com.am53.visadirect.fundstransactions.service.FundsTransactionsService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Visa Direct Funds API", description = "API for Visa Direct Funds endpoints.")
@RequestMapping("/funds-transactions/v1")
public class FundsTransactionsController {

    private static final Logger log = LoggerFactory.getLogger(FundsTransactionsController.class);

    private final FundsTransactionsService service;

    @Autowired
    public FundsTransactionsController(FundsTransactionsService service) {
        this.service = service;
    }

    @Operation(summary = "Pull Funds Transactions")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Pull funds transactions.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JsonNode.class)
                            )
                    })
    })
    @PostMapping("/pull")
    public ResponseEntity<JsonNode> pullFundsTransaction(@RequestBody JsonNode request) {
        log.info("Controller for pull funds transaction");
        JsonNode response = service.pullFundsTransactions(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Push Funds Transactions")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Push funds transactions.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JsonNode.class)
                            )
                    })
    })
    @PostMapping("/push")
    public ResponseEntity<JsonNode> pushFundsTransaction(@RequestBody JsonNode request) {
        log.info("Controller for push funds transaction");
        JsonNode response = service.pushFundsTransactions(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Reverse Funds Transactions")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "reverse funds transactions.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JsonNode.class)
                            )
                    })
    })
    @PostMapping("/reverse")
    public ResponseEntity<JsonNode> reverseFundsTransaction(@RequestBody JsonNode request) {
        log.info("Controller for reverse funds transaction");
        JsonNode response = service.reverseFundsTransactions(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
