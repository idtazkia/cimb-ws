package id.ac.tazkia.payment.cimb.cimbws.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIMB3rdParty_InquiryRs", propOrder = {
    "inquiryRs"
})
public class CIMB3RdPartyInquiryRs {

    @XmlElement(name = "InquiryRs", required = true)
    private InquiryRs inquiryRs;

}
