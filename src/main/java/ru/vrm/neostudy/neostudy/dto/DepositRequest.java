package ru.vrm.neostudy.neostudy.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DepositRequest (

        @NotNull
        @DecimalMin("1000")
        @DecimalMax("10000000")
        BigDecimal amount,

        @NotNull
        @Min(1)
        @Max(60)
        Integer months,

        @NotNull
        @DecimalMin("1")
        @DecimalMax("20")
        BigDecimal rate
) {}
