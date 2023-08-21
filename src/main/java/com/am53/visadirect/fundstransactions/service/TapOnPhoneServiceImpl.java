package com.am53.visadirect.fundstransactions.service;

import com.am53.visadirect.fundstransactions.dto.TapOnPhoneMerchantRequest;
import com.am53.visadirect.fundstransactions.model.MerchantPaycoreTapOnPhone;
import com.am53.visadirect.fundstransactions.repository.MerchantPaycoreTapOnPhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TapOnPhoneServiceImpl implements TapOnPhoneService {

    private static final Logger log = LoggerFactory.getLogger(TapOnPhoneServiceImpl.class);

    private MerchantPaycoreTapOnPhoneRepository merchantPaycoreTapOnPhoneRepository;

    @Override
    public TapOnPhoneMerchantRequest createOrUpdateTapOnPhone(TapOnPhoneMerchantRequest request) {

        log.info("Agrega o actualiza ");

        return null;

    }

}