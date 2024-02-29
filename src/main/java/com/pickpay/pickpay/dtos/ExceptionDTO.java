package com.pickpay.pickpay.dtos;

import jakarta.validation.constraints.NotBlank;

public record ExceptionDTO(
    @NotBlank
    String message,

    @NotBlank
    String status
) {}
