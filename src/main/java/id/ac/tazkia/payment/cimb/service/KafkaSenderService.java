package id.ac.tazkia.payment.cimb.service;

import id.ac.tazkia.payment.cimb.dto.VaResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Transactional
public class KafkaSenderService {

    public void send(VaResponse vaResponse) {
    }
}
