package com.am53.brand.hub.onboarding.taponphone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantPaycoreTapOnPhoneId implements Serializable {

    @Column(name = "SAAS_ID", nullable = false)
    private String saasId;

    @Column(name = "MID_PAYCORE", nullable = false)
    private String midPaycore;

}
