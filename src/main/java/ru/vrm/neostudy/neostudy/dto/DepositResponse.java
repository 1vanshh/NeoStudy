package ru.vrm.neostudy.neostudy.dto;

import java.math.BigDecimal;

/**
 * Result of deposit calculation.
 *
 * @param total final deposit amount
 * @param profit earned profit
 */
public record DepositResponse (

        BigDecimal total,

        BigDecimal profit
) {}
