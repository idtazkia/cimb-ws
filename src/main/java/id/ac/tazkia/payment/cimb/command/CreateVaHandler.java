package id.ac.tazkia.payment.cimb.command;

import id.ac.tazkia.payment.cimb.dto.VaRequest;
import id.ac.tazkia.payment.cimb.dto.VaRequestStatus;
import id.ac.tazkia.payment.cimb.dto.VaResponse;
import id.ac.tazkia.payment.cimb.dto.VaStatus;
import id.ac.tazkia.payment.cimb.entity.VirtualAccount;
import id.ac.tazkia.payment.cimb.exception.InvoiceNumberAlreadyExistsException;
import id.ac.tazkia.payment.cimb.exception.VirtualAccountNumberAlreadyExistsException;
import id.ac.tazkia.payment.cimb.helper.PaymentServiceConstants;
import id.ac.tazkia.payment.cimb.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CreateVaHandler implements VaHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateVaHandler.class);

    @Value("${cimb.client-id}") private String clientId;

    @Autowired private PaymentService paymentService;

    @Override
    public boolean supports(VaStatus status) {
        return VaStatus.CREATE.equals(status);
    }

    @Override
    public VaResponse process(VaRequest request) {
        LOGGER.info("[VA-REQUEST-CREATE] : Incoming : {}", request);
        VaResponse vaResponse = new VaResponse();
        BeanUtils.copyProperties(request, vaResponse);

        VirtualAccount va = new VirtualAccount();
        BeanUtils.copyProperties(request, va);
        va.setAccountNumber(String.format("%-12s", request.getAccountNumber() ).replace(' ', '0'));

        if(StringUtils.hasText(request.getInvoiceType())) {
            va.setInvoiceType(PaymentServiceConstants.INVOICE_TYPE_PREFIX + request.getInvoiceType());
        } else {
            va.setInvoiceType(PaymentServiceConstants.INVOICE_TYPE_PREFIX + PaymentServiceConstants.INVOICE_TYPE_PREFIX_DEFAULT);
        }

        LOGGER.debug("[VA-REQUEST-CREATE] : {}",va);

        try {
            paymentService.create(va);
            vaResponse.setRequestStatus(VaRequestStatus.SUCCESS);
            vaResponse.setAccountNumber(clientId + va.getAccountNumber());
            LOGGER.info("[VA-REQUEST-CREATE] : Success : {}-{}-{}", va.getAccountNumber(), va.getName(), va.getAmount());
        } catch (VirtualAccountNumberAlreadyExistsException | InvoiceNumberAlreadyExistsException e){
            LOGGER.error("[VA-REQUEST-CREATE] : Error : {}", e.getMessage());
            vaResponse.setRequestStatus(VaRequestStatus.ERROR);
        }

        return vaResponse;
    }
}
