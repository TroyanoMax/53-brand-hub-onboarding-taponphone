package com.am53.brand.hub.onboarding.taponphone.controller;

import com.am53.brand.hub.onboarding.taponphone.dto.TapOnPhoneMerchantRequest;
import com.am53.brand.hub.onboarding.taponphone.dto.TapOnPhoneMerchantResponse;
import com.am53.brand.hub.onboarding.taponphone.service.TapOnPhoneService;
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
@Tag(name = "53 Brand Hub On Boarding TapOnPhone API", description = "53 Brand Hub On Boarding TapOnPhone API.")
@RequestMapping("/api")
public class TapOnPhoneController {

    private static final Logger log = LoggerFactory.getLogger(TapOnPhoneController.class);

    private final TapOnPhoneService service;

    @Autowired
    public TapOnPhoneController(TapOnPhoneService service) {
        this.service = service;
    }

    @Operation(summary = "Tap On Phone - Create or Update")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Tap On Phone - Create or Update.",
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

    @Operation(summary = "Tap On Phone - Get a Value")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Tap On Phone - Get a Value.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TapOnPhoneMerchantResponse.class)
                            )
                    })
    })
    @GetMapping("/TapOnPhone/{midPaycore}")
    public ResponseEntity<TapOnPhoneMerchantResponse> findTapOnPhoneMerchant(@PathVariable String midPaycore) {
        log.info("Controller for pull funds transaction");
        TapOnPhoneMerchantResponse response = service.findTapOnPhone(midPaycore);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
