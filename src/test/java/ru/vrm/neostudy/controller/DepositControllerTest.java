package ru.vrm.neostudy.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.vrm.neostudy.controller.DepositController;
import ru.vrm.neostudy.dto.DepositRequest;
import ru.vrm.neostudy.dto.DepositResponse;
import ru.vrm.neostudy.service.DepositCalculator;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepositController.class)
class DepositControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepositCalculator depositCalculator;

    @Test
    void shouldReturnCalculatedDeposit() throws Exception {
        DepositResponse response = new DepositResponse(
                new BigDecimal("108299.95"),
                new BigDecimal("8299.95")
        );

        when(depositCalculator.calculate(any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 100000,
                                  "months": 12,
                                  "rate": 8
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(108299.95))
                .andExpect(jsonPath("$.profit").value(8299.95));
    }
}
