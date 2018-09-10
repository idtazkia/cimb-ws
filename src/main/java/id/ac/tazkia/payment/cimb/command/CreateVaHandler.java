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
        VaResponse vaResponse = new VaResponse();
        BeanUtils.copyProperties(request, vaResponse);

        VirtualAccount va = new VirtualAccount();
        BeanUtils.copyProperties(request, va);
        va.setAccountNumber(clientId + String.format("%-12s", request.getAccountNumber() ).replace(' ', '0'));
        va.setInvoiceType(PaymentServiceConstants.INVOICE_TYPE_PREFIX+request.getInvoiceType());

        LOGGER.debug("[VA-REQUEST-CREATE] : {}",va);

        try {
            paymentService.create(va);
            vaResponse.setRequestStatus(VaRequestStatus.SUCCESS);
        } catch (VirtualAccountNumberAlreadyExistsException | InvoiceNumberAlreadyExistsException e){
            LOGGER.error(e.getMessage(), e);
            vaResponse.setRequestStatus(VaRequestStatus.ERROR);
        }

        return vaResponse;
    }
}
