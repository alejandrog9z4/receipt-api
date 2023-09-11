package com.fetch.receipt.repository;

import com.fetch.receipt.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {

}
