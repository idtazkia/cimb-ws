package id.ac.tazkia.payment.cimb.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import id.ac.tazkia.payment.cimb.entity.*;

import java.util.Optional;

public interface VirtualAccountDao extends PagingAndSortingRepository<VirtualAccount, String> {
    Optional<VirtualAccount> findByAccountNumberAndAccountStatus(String accountNumber, AccountStatus status);
}