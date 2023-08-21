package com.am53.visadirect.fundstransactions.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Embeddable
@Data
public class MerchantPaycoreTapOnPhoneId implements Serializable {

    @Column(name = "SAAS_ID", nullable = false)
    private String saasId;

    @Column(name = "MID_PAYCORE", nullable = false)
    private String midPaycore;

}
