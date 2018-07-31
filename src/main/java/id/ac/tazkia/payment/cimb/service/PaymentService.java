package id.ac.tazkia.payment.cimb.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.ac.tazkia.payment.cimb.dao.*;
import id.ac.tazkia.payment.cimb.entity.*;
import id.ac.tazkia.payment.cimb.exception.*;

@Service @Transactional(rollbackFor=PaymentServiceException.class)
public class PaymentService {
    @Autowired private VirtualAccountDao virtualAccountDao;
    @Autowired private PaymentDao paymentDao;

    public VirtualAccount findByAccountNumber(String accountNumber) throws InvalidRequestException, VirtualAccountNotFoundException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Payment pay(VirtualAccount va, BigDecimal amount) throws InvalidRequestException, VirtualAccountNotFoundException, PaymentAmountMismatchException, VirtualAccountAlreadyPaidException {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}