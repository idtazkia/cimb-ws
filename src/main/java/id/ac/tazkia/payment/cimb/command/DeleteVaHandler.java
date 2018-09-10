package id.ac.tazkia.payment.cimb.command;

import id.ac.tazkia.payment.cimb.dto.VaRequest;
import id.ac.tazkia.payment.cimb.dto.VaRequestStatus;
import id.ac.tazkia.payment.cimb.dto.VaResponse;
import id.ac.tazkia.payment.cimb.dto.VaStatus;
import id.ac.tazkia.payment.cimb.exception.VirtualAccountAlreadyPaidException;
import id.ac.tazkia.payment.cimb.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteVaHandler implements VaHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteVaHandler.class);

    @Autowired private PaymentService paymentService;

    @Override
    public boolean supports(VaStatus status) {
        return VaStatus.DELETE.equals(status);
    }

    @Override
    public VaResponse process(VaRequest request)  {
        VaResponse vaResponse = new VaResponse();
        BeanUtils.copyProperties(request, vaResponse);

        try {
            paymentService.delete(request.getAccountNumber());
            vaResponse.setRequestStatus(VaRequestStatus.SUCCESS);
        } catch (VirtualAccountAlreadyPaidException e) {
            LOGGER.error(e.getMessage(), e);
            vaResponse.setRequestStatus(VaRequestStatus.ERROR);
        }

        return vaResponse;
    }
}
