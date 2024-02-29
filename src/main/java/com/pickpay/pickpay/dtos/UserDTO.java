package com.pickpay.pickpay.dtos;

import java.math.BigDecimal;

import com.pickpay.pickpay.domains.user.UserModelType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UserDTO(
    @NotBlank
    String name,

    @NotBlank
    String email,

    @NotBlank
    String password,

    @NotBlank
    String document,

    @NotNull
    @PositiveOrZero
    BigDecimal balance,

    @NotNull
    UserModelType type
) {}
