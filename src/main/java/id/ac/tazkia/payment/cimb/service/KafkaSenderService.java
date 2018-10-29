package id.ac.tazkia.payment.cimb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.tazkia.payment.cimb.dto.VaPayment;
import id.ac.tazkia.payment.cimb.dto.VaResponse;
import id.ac.tazkia.payment.cimb.entity.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Transactional
public class KafkaSenderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSenderService.class);

    @Value("${cimb.bank-id}") private String bankId;
    @Value("${cimb.client-id}") private String bankClientId;
    @Value("${kafka.topic.va.response}") private String kafkaTopicResponse;
    @Value("${kafka.topic.va.payment}") private String kafkaTopicPayment;

    @Autowired private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired private ObjectMapper objectMapper;

    public void send(VaResponse vaResponse) {
        try {
            String jsonResponse = objectMapper.writeValueAsString(vaResponse);
            LOGGER.debug("VA Response : {}", jsonResponse);
            kafkaTemplate.send(kafkaTopicResponse, jsonResponse);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public void send(Payment payment) {
        try {
            VaPayment vaPayment = new VaPayment();
            vaPayment.setBankId(bankId);
            vaPayment.setInvoiceNumber(payment.getVirtualAccount().getInvoiceNumber());
            vaPayment.setAccountNumber(accountToVaNumber(payment.getVirtualAccount().getAccountNumber()));
            vaPayment.setAmount(payment.getAmount());
            vaPayment.setCumulativeAmount(payment.getVirtualAccount().getCumulativePayment());
            vaPayment.setPaymentTime(payment.getTransactionTime());
            vaPayment.setReference(payment.getClientReference());
            String jsonData = objectMapper.writeValueAsString(vaPayment);
            LOGGER.debug("VA Payment : {}", jsonData);
            kafkaTemplate.send(kafkaTopicPayment, jsonData);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private String accountToVaNumber(String accountNumber) {
        return bankClientId + accountNumber;
    }
}
