package id.ac.tazkia.payment.cimb.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InquiryRq", propOrder = {
    "transactionID",
    "channelID",
    "terminalID",
    "transactionDate",
    "companyCode",
    "customerKey1",
    "customerKey2",
    "customerKey3",
    "additionalData1",
    "additionalData2",
    "additionalData3",
    "additionalData4"
})
public class InquiryRq {

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
    @XmlElement(name = "AdditionalData1", required = true)
    private String additionalData1;
    @XmlElement(name = "AdditionalData2", required = true)
    private String additionalData2;
    @XmlElement(name = "AdditionalData3", required = true)
    private String additionalData3;
    @XmlElement(name = "AdditionalData4", required = true)
    private String additionalData4;

}
