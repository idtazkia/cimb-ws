package id.ac.tazkia.payment.cimb.command;

import id.ac.tazkia.payment.cimb.dto.VaRequest;
import id.ac.tazkia.payment.cimb.dto.VaResponse;
import id.ac.tazkia.payment.cimb.dto.VaStatus;

public interface VaHandler {
    boolean supports(VaStatus status);
    VaResponse process(VaRequest request);
}
