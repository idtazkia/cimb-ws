package id.ac.tazkia.payment.cimb.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidRequestException extends PaymentServiceException {
    public InvalidRequestException(String msg){
        super(msg);
    }
}