package com.pickpay.pickpay.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pickpay.pickpay.domains.transaction.TransactionModel;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {} 