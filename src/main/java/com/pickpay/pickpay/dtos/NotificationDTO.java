package com.pickpay.pickpay.dtos;

import jakarta.validation.constraints.NotBlank;

public record NotificationDTO(
    @NotBlank
    String email,

    @NotBlank
    String message
) {}
