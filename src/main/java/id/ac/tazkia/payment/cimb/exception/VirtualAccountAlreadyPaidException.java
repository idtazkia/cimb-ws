package id.ac.tazkia.payment.cimb.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VirtualAccountAlreadyPaidException extends PaymentServiceException {
    public VirtualAccountAlreadyPaidException(String msg){
        super(msg);
    }
}