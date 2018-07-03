package id.ac.tazkia.payment.cimb.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillDetailList", propOrder = {
    "billDetail"
})
public class BillDetailList {

    @XmlElement(name = "BillDetail", required = true)
    private BillDetail billDetail;

}
