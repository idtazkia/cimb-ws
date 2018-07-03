package id.ac.tazkia.payment.cimb.cimbws.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(
        name = "CIMB3rdParty_InquiryRq",
        namespace = "http://CIMB3rdParty/BillPaymentWS"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIMB3rdParty_InquiryRq", propOrder = {
    "inquiryRq"
})
public class CIMB3RdPartyInquiryRq {

    @XmlElement(name = "InquiryRq", required = true)
    private InquiryRq inquiryRq;

}
