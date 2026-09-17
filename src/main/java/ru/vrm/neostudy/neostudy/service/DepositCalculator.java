package ru.vrm.neostudy.neostudy.service;

import ru.vrm.neostudy.neostudy.dto.DepositRequest;
import ru.vrm.neostudy.neostudy.dto.DepositResponse;

public interface DepositCalculator {


    /**
     * Calculates a deposit with monthly capitalization.
     *
     * <p>Formula:
     * total = amount * (1 + rate / 100 / 12) ^ months
     * </p>
     *
     * @param depositRequest deposit parameters
     * @return response containing total amount and profit
     */
    DepositResponse calculate(DepositRequest depositRequest);
}
