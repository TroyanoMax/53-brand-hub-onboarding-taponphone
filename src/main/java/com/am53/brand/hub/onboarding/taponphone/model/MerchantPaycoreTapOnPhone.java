package com.am53.brand.hub.onboarding.taponphone.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "MERCHANT_PAYCORE_TAP_ON_PHONE")
@Data
public class MerchantPaycoreTapOnPhone {

    @EmbeddedId
    private MerchantPaycoreTapOnPhoneId id;

    @Column(name = "TID_PAYCORE", nullable = false)
    private Long tidPaycore;

    @Column(name = "STATUS", nullable = false)
    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "STATUS", insertable = false, updatable = false)
    private String statusComment;

    @PrePersist
    protected void onCreate() {
        createDate = new Date();
    }

}