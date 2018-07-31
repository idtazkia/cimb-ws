package id.ac.tazkia.payment.cimb.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymentAmountMismatchException extends PaymentServiceException {
    public PaymentAmountMismatchException(String message){
        super(message);
    }
}