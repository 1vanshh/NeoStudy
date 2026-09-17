package ru.vrm.neostudy.neostudy.dto;

import java.math.BigDecimal;

public record DepositResponse (

        BigDecimal total,

        BigDecimal profit
) {}
