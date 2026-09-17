package ru.vrm.neostudy.neostudy.service.impl;

import org.springframework.stereotype.Service;
import ru.vrm.neostudy.neostudy.dto.DepositRequest;
import ru.vrm.neostudy.neostudy.dto.DepositResponse;
import ru.vrm.neostudy.neostudy.service.DepositCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DepositServiceImpl implements DepositCalculator {

    @Override
    public DepositResponse calculate(DepositRequest depositRequest) {

        BigDecimal monthlyRate = depositRequest.rate()
                .divide(BigDecimal.valueOf(1200), 10, RoundingMode.HALF_UP);

        BigDecimal total = depositRequest.amount()
                .multiply(
                        BigDecimal.ONE
                                .add(monthlyRate)
                                .pow(depositRequest.months())
                )
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal profit = total
                .subtract(depositRequest.amount())
                .setScale(2, RoundingMode.HALF_UP);

        return  new DepositResponse(total, profit);
    }
}
