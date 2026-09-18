package ru.vrm.neostudy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vrm.neostudy.dto.DepositRequest;
import ru.vrm.neostudy.dto.DepositResponse;
import ru.vrm.neostudy.service.DepositCalculator;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DepositController {

    private final DepositCalculator depositCalculator;

    @PostMapping("/calculate")
    public ResponseEntity<DepositResponse> calculate(
            @Valid @RequestBody DepositRequest depositRequest) {

        return ResponseEntity.ok(depositCalculator.calculate(depositRequest));
    }
}
