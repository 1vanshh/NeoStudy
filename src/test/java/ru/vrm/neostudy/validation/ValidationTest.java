package ru.vrm.neostudy.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.vrm.neostudy.controller.DepositController;
import ru.vrm.neostudy.service.DepositCalculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepositController.class)
class ValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepositCalculator depositCalculator;

    @Test
    void shouldReturnBadRequestWhenAmountIsTooSmall() throws Exception {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 999,
                                  "months": 12,
                                  "rate": 8
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.amount").exists());
    }

    @Test
    void shouldReturnBadRequestWhenAmountExceedsMaximum() throws Exception {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 10000001,
                                  "months": 12,
                                  "rate": 8
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.amount").exists());
    }

    @Test
    void shouldReturnBadRequestWhenMonthsIsTooSmall() throws Exception {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 100000,
                                  "months": 0,
                                  "rate": 8
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.months").exists());
    }

    @Test
    void shouldReturnBadRequestWhenMonthsExceedMaximum() throws Exception {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 100000,
                                  "months": 61,
                                  "rate": 8
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.months").exists());
    }

    @Test
    void shouldReturnBadRequestWhenRateIsTooSmall() throws Exception {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 100000,
                                  "months": 12,
                                  "rate": 0.99
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rate").exists());
    }

    @Test
    void shouldReturnBadRequestWhenRateExceedsMaximum() throws Exception {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 100000,
                                  "months": 12,
                                  "rate": 20.01
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rate").exists());
    }

    @Test
    void shouldReturnBadRequestWhenAmountIsMissing() throws Exception {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "months": 12,
                                  "rate": 8
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.amount").exists());
    }

    @Test
    void shouldReturnBadRequestWhenMonthsIsMissing() throws Exception {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 100000,
                                  "rate": 8
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.months").exists());
    }

    @Test
    void shouldReturnBadRequestWhenRateIsMissing() throws Exception {
        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 100000,
                                  "months": 12
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rate").exists());
    }
}
