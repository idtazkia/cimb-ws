package id.ac.tazkia.payment.cimb.dao;

import id.ac.tazkia.payment.cimb.entity.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentDao extends PagingAndSortingRepository<Payment, String> {}