package id.ac.tazkia.payment.cimb.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @Entity
public class VirtualAccount {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    private String accountNumber;

    @NotNull @NotEmpty
    private String invoiceNumber;

    @NotNull @NotEmpty
    private String invoiceType;

    @NotNull @NotEmpty
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull @Min(0)
    private BigDecimal amount;

    @NotNull @Min(0)
    private BigDecimal cumulativePayment = BigDecimal.ZERO;

    private String description;

    @Email
    private String email;

    private String phone;

    @NotNull
    private LocalDateTime createTime;

    @NotNull @Column(columnDefinition = "DATE")
    private LocalDate expireDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public BigDecimal effectiveAmount(){
        if(AccountType.INSTALLMENT.equals(accountType)){
            return amount.subtract(cumulativePayment);
        }

        return amount;
    }
}
