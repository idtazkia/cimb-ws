package id.ac.tazkia.payment.cimb.controller;

import id.ac.tazkia.payment.cimb.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.util.UUID;

@Endpoint
public class CimbWsEndpoint {
    private static final String NAMESPACE_URI = "http://CIMB3rdParty/BillPaymentWS";
    private static final Logger LOGGER = LoggerFactory.getLogger(CimbWsEndpoint.class);

    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "CIMB3rdParty_InquiryRq"
    )
    @ResponsePayload
    public CIMB3RdPartyInquiryRs inquiry(@RequestPayload CIMB3RdPartyInquiryRq request) {
        InquiryRq inquiryRq = request.getInquiryRq();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Customer Key 1 : {}",inquiryRq.getCustomerKey1());
        }


        BillDetail billDetail = new BillDetail();
        billDetail.setBillAmount(new BigDecimal(123456.78));
        billDetail.setBillReference(UUID.randomUUID().toString().replaceAll("-", ""));
        billDetail.setBillCode("123");
        billDetail.setBillCurrency("360");

        BillDetailList billDetailList = new BillDetailList();
        billDetailList.setBillDetail(billDetail);

        InquiryRs inquiryRs = new InquiryRs();
        inquiryRs.setCustomerKey1(inquiryRq.getCustomerKey1());
        inquiryRs.setCustomerName("Test Customer 001");

        CIMB3RdPartyInquiryRs response = new CIMB3RdPartyInquiryRs();
        response.setInquiryRs(inquiryRs);

        return response;
    }

    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "CIMB3rdParty_PaymentRq"
    )
    @ResponsePayload
    public CIMB3RdPartyPaymentRs payment(@RequestPayload CIMB3RdPartyPaymentRq request) {
        PaymentRq paymentRq = request.getPaymentRq();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Customer : {}", paymentRq.getCustomerKey1());
            LOGGER.debug("Amount : {}", paymentRq.getPaidAmount());
        }

        PaymentRs paymentRs = new PaymentRs();
        paymentRs.setReferenceNumberTransaction(
                UUID.randomUUID().toString().replaceAll("-", ""));

        CIMB3RdPartyPaymentRs response = new CIMB3RdPartyPaymentRs();
        response.setPaymentRs(paymentRs);

        return response;
    }
}
