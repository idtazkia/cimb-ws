package id.ac.tazkia.payment.cimb.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VirtualAccountNotFoundException extends PaymentServiceException {
    public VirtualAccountNotFoundException(String message) {
        super(message);
    }
}