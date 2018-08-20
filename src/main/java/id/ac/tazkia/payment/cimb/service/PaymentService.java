package id.ac.tazkia.payment.cimb.service;

import id.ac.tazkia.payment.cimb.dao.PaymentDao;
import id.ac.tazkia.payment.cimb.dao.VirtualAccountDao;
import id.ac.tazkia.payment.cimb.entity.*;
import id.ac.tazkia.payment.cimb.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Service @Transactional(rollbackFor=PaymentServiceException.class)
public class PaymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    @Autowired private VirtualAccountDao virtualAccountDao;
    @Autowired private PaymentDao paymentDao;

    public VirtualAccount findByAccountNumber(String accountNumber) throws VirtualAccountNotFoundException {
        if (!StringUtils.hasText(accountNumber)){
            throw new VirtualAccountNotFoundException("Account number empty");
        }
        return virtualAccountDao.findByAccountNumberAndAccountStatus(accountNumber, AccountStatus.ACTIVE)
                .orElseThrow(() -> new VirtualAccountNotFoundException("Account number "+accountNumber+ " not found"));
    }

    public Payment pay(String accountNumber, BigDecimal amount, String reference) throws InvalidRequestException, VirtualAccountNotFoundException, PaymentAmountMismatchException, VirtualAccountAlreadyPaidException {
        if (!StringUtils.hasText(reference)) {
            throw new InvalidRequestException("Payment Reference missing");
        }
        VirtualAccount va = virtualAccountDao.findByAccountNumberAndAccountStatus(accountNumber, AccountStatus.ACTIVE)
                .orElseThrow(() -> new VirtualAccountNotFoundException("Account number "+accountNumber+ " not found"));


        if(AccountType.CLOSED.equals(va.getAccountType())) {
            if (va.getAmount().compareTo(amount) != 0) {
                throw new PaymentAmountMismatchException("Amount sent " + amount + ", should be " + va.getAmount());
            }
        }

        if (AccountType.OPEN.equals(va.getAccountType())) {
            if (va.getAmount().compareTo(amount) > 0) {
                throw new PaymentAmountMismatchException("Amount sent " + amount + ", should be greater than " + va.getAmount());
            }
        }

        if (AccountType.INSTALLMENT.equals(va.getAccountType())) {
            if (va.getAmount().compareTo(amount.add(va.getCumulativePayment())) < 0) {
                throw new PaymentAmountMismatchException("Amount sent " + amount + ", should be less than " + va.getAmount().subtract(va.getCumulativePayment()));
            }
        }

        va.setCumulativePayment(va.getCumulativePayment().add(amount));

        Payment payment = new Payment();
        payment.setVirtualAccount(va);
        payment.setAmount(amount);
        payment.setClientReference(reference);

        if(AccountType.OPEN.equals(va.getAccountType())) {
            payment.setPaymentStatus(PaymentStatus.PARTIAL);
        } else {
            if (va.getCumulativePayment().compareTo(va.getAmount()) == 0) {
                payment.setPaymentStatus(PaymentStatus.FULL);
                va.setAccountStatus(AccountStatus.INACTIVE);
            } else {
                payment.setPaymentStatus(PaymentStatus.PARTIAL);
            }
        }

        virtualAccountDao.save(va);
        paymentDao.save(payment);
        return payment;

    }
}