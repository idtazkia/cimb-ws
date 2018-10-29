package id.ac.tazkia.payment.cimb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.tazkia.payment.cimb.command.VaHandlerFactory;
import id.ac.tazkia.payment.cimb.dto.VaRequest;
import id.ac.tazkia.payment.cimb.dto.VaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Service @Transactional
public class KafkaListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerService.class);

    @Value("${cimb.bank-id}") private String bankId;

    @Autowired private KafkaSenderService kafkaSenderService;
    @Autowired private VaHandlerFactory vaHandlerFactory;
    @Autowired private ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic.va.request}")
    public void receiveVirtualAccountRequest(String message){
        LOGGER.info("[VA-REQUEST] - [MESSAGE]: {}", message);
        try {
            VaRequest vaRequest = objectMapper.readValue(message, VaRequest.class);
            if (!bankId.equals(vaRequest.getBankId())) {
                LOGGER.info("VA Request for bank {}, skipped", vaRequest.getBankId());
                return;
            }
            VaResponse vaResponse = vaHandlerFactory
                    .getHandler(vaRequest.getRequestType()).process(vaRequest);
            kafkaSenderService.send(vaResponse);
            LOGGER.info("[VA-RESPONSE] - [MESSAGE]: {}", vaResponse);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
