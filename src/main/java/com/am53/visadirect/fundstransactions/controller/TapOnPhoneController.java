package com.am53.visadirect.fundstransactions.controller;

import com.am53.visadirect.fundstransactions.dto.TapOnPhoneMerchantRequest;
import com.am53.visadirect.fundstransactions.service.TapOnPhoneService;
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
@RequestMapping("/api")
public class TapOnPhoneController {

    private static final Logger log = LoggerFactory.getLogger(TapOnPhoneController.class);

    private final TapOnPhoneService service;

    @Autowired
    public TapOnPhoneController(TapOnPhoneService service) {
        this.service = service;
    }

    @Operation(summary = "Tap On Phone")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Tap On Phone.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TapOnPhoneMerchantRequest.class)
                            )
                    })
    })
    @PostMapping("/TapOnPhone")
    public ResponseEntity<TapOnPhoneMerchantRequest> createOrUpdateTapOnPhoneMerchant(@RequestBody TapOnPhoneMerchantRequest request) {
        log.info("Controller for pull funds transaction");
        TapOnPhoneMerchantRequest response = service.createOrUpdateTapOnPhone(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
