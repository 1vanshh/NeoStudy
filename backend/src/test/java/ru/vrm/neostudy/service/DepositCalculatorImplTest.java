package ru.vrm.neostudy.service;

import org.junit.jupiter.api.Test;
import ru.vrm.neostudy.dto.DepositRequest;
import ru.vrm.neostudy.dto.DepositResponse;
import ru.vrm.neostudy.service.DepositCalculator;
import ru.vrm.neostudy.service.impl.DepositCalculatorImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class DepositCalculatorImplTest {

    private final DepositCalculator calculator =
            new DepositCalculatorImpl();


    @Test
    void shouldCalculateDepositCorrectly() {
        DepositRequest request = new DepositRequest(
                new BigDecimal("100000"),
                12,
                new BigDecimal("8")
        );

        DepositResponse response = calculator.calculate(request);

        assertEquals(
                new BigDecimal("108299.95"),
                response.total()
        );

        assertEquals(
                new BigDecimal("8299.95"),
                response.profit()
        );
    }

    @Test
    void shouldCalculateDepositWithMinimumValues() {
        DepositRequest request = new DepositRequest(
                new BigDecimal("1000"),
                1,
                new BigDecimal("1")
        );

        DepositResponse response = calculator.calculate(request);

        assertEquals(
                new BigDecimal("1000.83"),
                response.total()
        );

        assertEquals(
                new BigDecimal("0.83"),
                response.profit()
        );
    }

    @Test
    void shouldCalculateDepositWithMaximumValues() {
        DepositRequest request = new DepositRequest(
                new BigDecimal("10000000"),
                60,
                new BigDecimal("20")
        );

        DepositResponse response = calculator.calculate(request);

        assertEquals(
                new BigDecimal("26959701.45"),
                response.total()
        );

        assertEquals(
                new BigDecimal("16959701.45"),
                response.profit()
        );
    }
}
