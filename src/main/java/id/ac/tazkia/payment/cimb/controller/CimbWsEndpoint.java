package id.ac.tazkia.payment.cimb.controller;

import id.ac.tazkia.payment.cimb.dto.*;
import id.ac.tazkia.payment.cimb.entity.AccountType;
import id.ac.tazkia.payment.cimb.entity.Payment;
import id.ac.tazkia.payment.cimb.entity.VirtualAccount;
import id.ac.tazkia.payment.cimb.exception.InvalidRequestException;
import id.ac.tazkia.payment.cimb.exception.PaymentAmountMismatchException;
import id.ac.tazkia.payment.cimb.exception.VirtualAccountAlreadyPaidException;
import id.ac.tazkia.payment.cimb.exception.VirtualAccountNotFoundException;
import id.ac.tazkia.payment.cimb.helper.PaymentServiceConstants;
import id.ac.tazkia.payment.cimb.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Endpoint
public class CimbWsEndpoint {

    private static final String NAMESPACE_URI = "http://CIMB3rdParty/BillPaymentWS";
    private static final Logger LOGGER = LoggerFactory.getLogger(CimbWsEndpoint.class);

    @Autowired private PaymentService paymentService;

    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "CIMB3rdParty_InquiryRq"
    )
    @ResponsePayload
    public CIMB3RdPartyInquiryRs inquiry(@RequestPayload CIMB3RdPartyInquiryRq request) {
        CIMB3RdPartyInquiryRs response = new CIMB3RdPartyInquiryRs();
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("==== Incoming Inquiry Request : Start ====");
                LOGGER.debug(request.toString());
                LOGGER.debug("==== Incoming Inquiry Request : End ====");
            }

            InquiryRq inquiryRq = request.getInquiryRq();
            if (inquiryRq == null) {
                throw new InvalidRequestException("Inquiry request body empty");
            }

            BeanUtils.copyProperties(inquiryRq, response.getInquiryRs());

            String accountNumber = inquiryRq.getCustomerKey1();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Customer Key 1 : {}", accountNumber);
            }

            VirtualAccount va = paymentService.findByAccountNumber(accountNumber);
            fillResponseData(va, response);
            response.getInquiryRs().setResponseCode(PaymentServiceConstants.RC_SUCCESS);
            response.getInquiryRs().setResponseDescription(PaymentServiceConstants.MSG_SUCCESS);
            LOGGER.info("[INQUIRY] - [SUCCESS] : Acc [{}]-[{}], Rp. {}", accountNumber, va.getName(), va.getAmount());
        } catch (InvalidRequestException err) {
            LOGGER.warn("[INQUIRY] - [ERROR] : Acc [{}], Error : {}", request.getInquiryRq().getCustomerKey1(), err.getMessage());
            response.getInquiryRs().setResponseCode(PaymentServiceConstants.RC_GENERAL_FAILURE);
            response.getInquiryRs().setResponseDescription(PaymentServiceConstants.MSG_GENERAL_FAILURE);
        } catch (VirtualAccountNotFoundException err) {
            LOGGER.warn("[INQUIRY] - [ERROR] : Acc [{}], Error : {}", request.getInquiryRq().getCustomerKey1(), err.getMessage());
            response.getInquiryRs().setResponseCode(PaymentServiceConstants.RC_CUSTOMER_NOT_FOUND);
            response.getInquiryRs().setResponseDescription(PaymentServiceConstants.MSG_CUSTOMER_NOT_FOUND);
        } catch (Exception err){
            LOGGER.warn("[INQUIRY] - [ERROR] : Acc [{}], Error : {}", request.getInquiryRq().getCustomerKey1(), err.getMessage());
            response.getInquiryRs().setResponseCode(PaymentServiceConstants.RC_GENERAL_FAILURE);
            response.getInquiryRs().setResponseDescription(PaymentServiceConstants.MSG_GENERAL_FAILURE);
        }
        return response;
    }

    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "CIMB3rdParty_PaymentRq"
    )
    @ResponsePayload
    public CIMB3RdPartyPaymentRs payment(@RequestPayload CIMB3RdPartyPaymentRq request) {
        CIMB3RdPartyPaymentRs response = new CIMB3RdPartyPaymentRs();
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("==== Incoming Payment Request : Start ====");
                LOGGER.debug(request.toString());
                LOGGER.debug("==== Incoming Payment Request : End ====");
            }

            PaymentRq paymentRq = request.getPaymentRq();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Customer : {}", paymentRq.getCustomerKey1());
                LOGGER.debug("Amount : {}", paymentRq.getPaidAmount());
            }

            Payment payment = paymentService.pay(paymentRq.getCustomerKey1(), paymentRq.getPaidAmount(), paymentRq.getReferenceNumberTransaction());
            PaymentRs paymentRs = new PaymentRs();
            BeanUtils.copyProperties(paymentRq, paymentRs);
            paymentRs.setReferenceNumberTransaction(payment.getVirtualAccount().getId());
            paymentRs.setTransactionDate(DateTimeFormatter.ofPattern(PaymentServiceConstants.DATE_FORMAT).format(LocalDateTime.now()));
            paymentRs.setAmount(payment.getAmount().setScale(0, RoundingMode.HALF_EVEN));
            paymentRs.setFee(BigDecimal.ZERO.setScale(0, RoundingMode.HALF_EVEN));
            paymentRs.setPaidAmount(payment.getAmount().setScale(0, RoundingMode.HALF_EVEN));
            paymentRs.setPaymentFlag("100000");
            response.setPaymentRs(paymentRs);
            response.getPaymentRs().setResponseCode(PaymentServiceConstants.RC_SUCCESS);
            response.getPaymentRs().setResponseDescription(PaymentServiceConstants.MSG_SUCCESS);
            LOGGER.info("[PAYMENT] - [SUCCESS] : Acc [{}]-[{}], Rp [{}]", payment.getVirtualAccount().getAccountNumber(), payment.getVirtualAccount().getName(), payment.getAmount());
        } catch (InvalidRequestException err) {
            LOGGER.warn("[PAYMENT] - [ERROR] : Acc [{}], Rp [{}], Error [{}]", request.getPaymentRq().getCustomerKey1(), request.getPaymentRq().getPaidAmount(),err.getMessage());
            response.getPaymentRs().setResponseCode(PaymentServiceConstants.RC_GENERAL_FAILURE);
            response.getPaymentRs().setResponseDescription(PaymentServiceConstants.MSG_GENERAL_FAILURE);
        } catch (VirtualAccountNotFoundException err) {
            LOGGER.warn("[PAYMENT] - [ERROR] : Acc [{}], Rp [{}], Error [{}]", request.getPaymentRq().getCustomerKey1(), request.getPaymentRq().getPaidAmount(),err.getMessage());
            response.getPaymentRs().setResponseCode(PaymentServiceConstants.RC_CUSTOMER_NOT_FOUND);
            response.getPaymentRs().setResponseDescription(PaymentServiceConstants.MSG_CUSTOMER_NOT_FOUND);
        } catch (PaymentAmountMismatchException err) {
            LOGGER.warn("[PAYMENT] - [ERROR] : Acc [{}], Rp [{}], Error [{}]", request.getPaymentRq().getCustomerKey1(), request.getPaymentRq().getPaidAmount(),err.getMessage());
            response.getPaymentRs().setResponseCode(PaymentServiceConstants.RC_INVALID_AMOUNT);
            response.getPaymentRs().setResponseDescription(PaymentServiceConstants.MSG_INVALID_AMOUNT);
        } catch (VirtualAccountAlreadyPaidException err) {
            LOGGER.warn("[PAYMENT] - [ERROR] : Acc [{}], Rp [{}], Error [{}]", request.getPaymentRq().getCustomerKey1(), request.getPaymentRq().getPaidAmount(),err.getMessage());
            response.getPaymentRs().setResponseCode(PaymentServiceConstants.RC_ALREADY_PAID);
            response.getPaymentRs().setResponseDescription(PaymentServiceConstants.MSG_ALREADY_PAID);
        } catch (Exception err){
            LOGGER.warn("[PAYMENT] - [ERROR] : Acc [{}], Rp [{}], Error [{}]", request.getPaymentRq().getCustomerKey1(), request.getPaymentRq().getPaidAmount(),err.getMessage());
            response.getPaymentRs().setResponseCode(PaymentServiceConstants.RC_GENERAL_FAILURE);
            response.getPaymentRs().setResponseDescription(PaymentServiceConstants.MSG_GENERAL_FAILURE);
        }
        return response;
    }


    private void fillResponseData(VirtualAccount va, CIMB3RdPartyInquiryRs response) {
        BillDetail billDetail = new BillDetail();
        billDetail.setBillAmount(va.effectiveAmount().setScale(0, RoundingMode.HALF_EVEN));
        billDetail.setBillReference(va.getId());
        billDetail.setBillCode(va.getInvoiceType());
        billDetail.setBillCurrency(PaymentServiceConstants.BILL_CURRENCY);

        BillDetailList billDetailList = new BillDetailList();
        billDetailList.setBillDetail(billDetail);

        response.getInquiryRs().setCurrency(PaymentServiceConstants.BILL_CURRENCY);
        response.getInquiryRs().setBillDetailList(billDetailList);
        response.getInquiryRs().setAmount(va.effectiveAmount().setScale(0, RoundingMode.HALF_EVEN));
        response.getInquiryRs().setFee(BigDecimal.ZERO.setScale(0, RoundingMode.HALF_EVEN));
        response.getInquiryRs().setPaidAmount(va.getAmount().setScale(0, RoundingMode.HALF_EVEN));
        response.getInquiryRs().setCustomerName(va.getName());

        if (AccountType.CLOSED.equals(va.getAccountType())) {
            response.getInquiryRs().setFlagPayment("1");
        } else {
            response.getInquiryRs().setFlagPayment("0");
        }
    }
}
