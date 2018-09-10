package id.ac.tazkia.payment.cimb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VaResponse {
    private VaStatus requestType;
    private VaRequestStatus requestStatus;
    private String accountNumber;
    private String invoiceNumber;
    private String name;
    private BigDecimal amount;
    private String bankId;
}
