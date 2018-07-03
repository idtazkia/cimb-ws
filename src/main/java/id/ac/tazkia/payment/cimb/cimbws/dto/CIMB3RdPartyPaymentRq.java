package id.ac.tazkia.payment.cimb.cimbws.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(
        name = "CIMB3rdParty_PaymentRq",
        namespace = "http://CIMB3rdParty/BillPaymentWS"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIMB3rdParty_PaymentRq", propOrder = {
    "paymentRq"
})
public class CIMB3RdPartyPaymentRq {

    @XmlElement(name = "PaymentRq", required = true)
    private PaymentRq paymentRq;

}
