package id.ac.tazkia.payment.cimb.command;

import id.ac.tazkia.payment.cimb.dto.VaRequest;
import id.ac.tazkia.payment.cimb.dto.VaResponse;
import id.ac.tazkia.payment.cimb.dto.VaStatus;

public class UnsupportedVaHandler implements VaHandler {

    @Override
    public boolean supports(VaStatus status) {
        return true;
    }

    @Override
    public VaResponse process(VaRequest request) {
        throw new UnsupportedOperationException("Request Type "+request.getRequestType()+" is not supported");
    }
}
