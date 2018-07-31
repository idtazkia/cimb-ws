package id.ac.tazkia.payment.cimb.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import id.ac.tazkia.payment.cimb.entity.*;

public interface VirtualAccountDao extends PagingAndSortingRepository<VirtualAccount, String> {

}