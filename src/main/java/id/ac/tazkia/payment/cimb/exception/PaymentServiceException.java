package id.ac.tazkia.payment.cimb.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymentServiceException extends Exception {
    public PaymentServiceException(String msg){
        super(msg);
    }
}