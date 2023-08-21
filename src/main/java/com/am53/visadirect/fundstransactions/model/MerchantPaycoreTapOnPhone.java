package com.am53.visadirect.fundstransactions.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "STATUS", insertable = false, updatable = false)
    private String statusComment;

}