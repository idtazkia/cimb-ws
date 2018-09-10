package id.ac.tazkia.payment.cimb.dto;

import id.ac.tazkia.payment.cimb.entity.AccountType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VaRequest {
    private String bankId;
    private String accountNumber;
    private String invoiceNumber;
    private String name;
    private String description;
    private String email;
    private String phone;
    private BigDecimal amount;
    private LocalDate expireDate;
    private String invoiceType;
    private AccountType accountType = AccountType.CLOSED;
    private VaStatus requestType = VaStatus.CREATE;
}
