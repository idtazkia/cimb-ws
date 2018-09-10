package id.ac.tazkia.payment.cimb.dao;

import id.ac.tazkia.payment.cimb.entity.AccountStatus;
import id.ac.tazkia.payment.cimb.entity.VirtualAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface VirtualAccountDao extends PagingAndSortingRepository<VirtualAccount, String> {
    Optional<VirtualAccount> findByAccountNumberAndAccountStatus(String accountNumber, AccountStatus status);

    Optional<VirtualAccount> findByInvoiceNumber(String invoiceNumber);
}