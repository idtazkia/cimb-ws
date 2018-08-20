package id.ac.tazkia.payment.cimb.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillDetail", propOrder = {
    "billCurrency",
    "billCode",
    "billAmount",
    "billReference"
})
public class BillDetail {
    @XmlElement(name = "BillCurrency", required = true)
    private String billCurrency;
    @XmlElement(name = "BillCode", required = true)
    private String billCode;
    @XmlElement(name = "BillAmount", required = true)
    private BigDecimal billAmount;
    @XmlElement(name = "BillReference", required = true)
    private String billReference;
}
