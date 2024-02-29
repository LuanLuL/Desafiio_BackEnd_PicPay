package com.pickpay.pickpay.dtos;

import java.math.BigDecimal;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionDTO(
    @NotNull
    UUID senderId, 
    
    @NotNull
    UUID receiverId,

    @NotNull
    @Positive
    BigDecimal value
) {}
