package ru.vrm.neostudy.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vrm.neostudy.dto.DepositRequest;
import ru.vrm.neostudy.dto.DepositResponse;
import ru.vrm.neostudy.service.DepositCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
public class DepositCalculatorImpl implements DepositCalculator {

    @Override
    public DepositResponse calculate(DepositRequest depositRequest) {

        log.debug("Calculating deposit: amount={}, rate={}, months={}",
                depositRequest.amount(),
                depositRequest.rate(),
                depositRequest.months()
        );

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

        log.debug("Deposit calculated: total={}, profit={}",
                total,
                profit
        );

        return  new DepositResponse(total, profit);
    }
}
