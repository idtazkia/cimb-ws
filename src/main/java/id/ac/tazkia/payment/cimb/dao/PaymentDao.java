package id.ac.tazkia.payment.cimb.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import id.ac.tazkia.payment.cimb.entity.Payment;

public interface PaymentDao extends PagingAndSortingRepository<Payment, String> {}