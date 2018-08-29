package id.ac.tazkia.payment.cimb.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "CIMB3rdParty_PaymentRs")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIMB3rdParty_PaymentRs", propOrder = {
    "paymentRs"
})
public class CIMB3RdPartyPaymentRs {

    @XmlElement(name = "PaymentRs", required = true)
    private PaymentRs paymentRs = new PaymentRs();

}
