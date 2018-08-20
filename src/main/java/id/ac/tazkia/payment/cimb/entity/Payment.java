package id.ac.tazkia.payment.cimb.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @Entity
public class Payment {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_virtual_account")
    @NotNull
    private VirtualAccount virtualAccount;

    @NotNull @Min(1)
    private BigDecimal amount;

    @NotEmpty
    private String clientReference;

    @NotNull
    private LocalDateTime transactionTime = LocalDateTime.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
