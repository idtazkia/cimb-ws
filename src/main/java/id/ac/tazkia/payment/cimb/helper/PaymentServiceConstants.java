package id.ac.tazkia.payment.cimb.helper;

public abstract class PaymentServiceConstants {
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSS";
    public static final String BILL_CURRENCY = "IDR";
    public static final String RC_CUSTOMER_NOT_FOUND = "16";
    public static final String MSG_CUSTOMER_NOT_FOUND = "Customer Not Found";
    public static final String RC_ALREADY_PAID = "33";
    public static final String MSG_ALREADY_PAID = "Bill Already Paid";
    public static final String RC_INVALID_AMOUNT = "38";
    public static final String MSG_INVALID_AMOUNT = "Invalid Amount";
    public static final String RC_GENERAL_FAILURE = "99";
    public static final String MSG_GENERAL_FAILURE = "General Failure";
    public static final String RC_SUCCESS = "00";
    public static final String MSG_SUCCESS = "Transaction Success";
    public static final String INVOICE_TYPE_PREFIX = "TAZKIA00";
    public static final String INVOICE_TYPE_PREFIX_DEFAULT = "00";
}