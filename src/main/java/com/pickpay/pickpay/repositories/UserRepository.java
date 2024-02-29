package com.pickpay.pickpay.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pickpay.pickpay.domains.user.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {}
