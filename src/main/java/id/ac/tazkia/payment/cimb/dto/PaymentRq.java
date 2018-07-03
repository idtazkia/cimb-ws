package id.ac.tazkia.payment.cimb.dto;

import lombok.Data;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentRq", propOrder = {
    "transactionID",
    "channelID",
    "terminalID",
    "transactionDate",
    "companyCode",
    "customerKey1",
    "customerKey2",
    "customerKey3",
    "language",
    "currency",
    "amount",
    "fee",
    "paidAmount",
    "referenceNumberTransaction",
    "flagPaymentList",
    "customerName",
    "additionalData1",
    "additionalData2",
    "additionalData3",
    "additionalData4"
})
public class PaymentRq {

    @XmlElement(name = "TransactionID", required = true)
    private String transactionID;
    @XmlElement(name = "ChannelID", required = true)
    private String channelID;
    @XmlElement(name = "TerminalID", required = true)
    private String terminalID;
    @XmlElement(name = "TransactionDate", required = true)
    private String transactionDate;
    @XmlElement(name = "CompanyCode", required = true)
    private String companyCode;
    @XmlElement(name = "CustomerKey1", required = true)
    private String customerKey1;
    @XmlElement(name = "CustomerKey2", required = true)
    private String customerKey2;
    @XmlElement(name = "CustomerKey3", required = true)
    private String customerKey3;
    @XmlElement(name = "Language", required = true)
    private String language;
    @XmlElement(name = "Currency", required = true)
    private String currency;
    @XmlElement(name = "Amount", required = true)
    private BigDecimal amount;
    @XmlElement(name = "Fee", required = true)
    private BigDecimal fee;
    @XmlElement(name = "PaidAmount", required = true)
    private BigDecimal paidAmount;
    @XmlElement(name = "ReferenceNumberTransaction", required = true)
    private String referenceNumberTransaction;
    @XmlElement(name = "FlagPaymentList", required = true)
    private String flagPaymentList;
    @XmlElement(name = "CustomerName", required = true)
    private String customerName;
    @XmlElement(name = "AdditionalData1", required = true)
    private String additionalData1;
    @XmlElement(name = "AdditionalData2", required = true)
    private String additionalData2;
    @XmlElement(name = "AdditionalData3", required = true)
    private String additionalData3;
    @XmlElement(name = "AdditionalData4", required = true)
    private String additionalData4;

}
