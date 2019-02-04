package id.ac.tazkia.payment.cimb.command;

import id.ac.tazkia.payment.cimb.dto.VaRequest;
import id.ac.tazkia.payment.cimb.dto.VaRequestStatus;
import id.ac.tazkia.payment.cimb.dto.VaResponse;
import id.ac.tazkia.payment.cimb.dto.VaStatus;
import id.ac.tazkia.payment.cimb.entity.VirtualAccount;
import id.ac.tazkia.payment.cimb.exception.VirtualAccountAlreadyPaidException;
import id.ac.tazkia.payment.cimb.exception.VirtualAccountNotFoundException;
import id.ac.tazkia.payment.cimb.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateVaHandler implements VaHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateVaHandler.class);

    @Autowired private PaymentService paymentService;

    @Override
    public boolean supports(VaStatus status) {
        return VaStatus.UPDATE.equals(status);
    }

    @Override
    public VaResponse process(VaRequest request)  {
        VaResponse vaResponse = new VaResponse();
        BeanUtils.copyProperties(request, vaResponse);

        try {
            VirtualAccount va = paymentService.findByAccountNumber(request.getAccountNumber());
            BeanUtils.copyProperties(request, va);
            paymentService.update(va);
            vaResponse.setRequestStatus(VaRequestStatus.SUCCESS);
        } catch (VirtualAccountAlreadyPaidException | VirtualAccountNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            vaResponse.setRequestStatus(VaRequestStatus.ERROR);
        }

        return vaResponse;
    }
}
