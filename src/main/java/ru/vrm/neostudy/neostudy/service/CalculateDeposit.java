package ru.vrm.neostudy.neostudy.service;

import ru.vrm.neostudy.neostudy.dto.DepositRequest;
import ru.vrm.neostudy.neostudy.dto.DepositResponse;

public interface CalculateDeposit {


    /**
     *
     * @param depositRequest
     * @return
     */
    public DepositResponse calculateDeposit(DepositRequest depositRequest);
}
