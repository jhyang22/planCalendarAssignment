package com.sparta.plancalendarassignment.controller;

import com.sparta.plancalendarassignment.dto.PlanCreateResponseDto;
import com.sparta.plancalendarassignment.dto.PlanRequestDto;
import com.sparta.plancalendarassignment.dto.PlanResponseDto;
import com.sparta.plancalendarassignment.dto.PlanUpdateResponseDto;
import com.sparta.plancalendarassignment.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    // 일정 생성
    @PostMapping
    public ResponseEntity<PlanCreateResponseDto> createPlan(@Valid @RequestBody PlanRequestDto dto) {

        return new ResponseEntity<>(planService.savePlan(dto), HttpStatus.CREATED);
    }

    // 일정 전체 조회
    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> findAllPlans() {

        return new ResponseEntity<>(planService.findAllPlans(), HttpStatus.OK);
    }

    // 일정 일부 조회
    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponseDto> findPlanById(@PathVariable Long planId) {

        return new ResponseEntity<>(planService.findPlanById(planId), HttpStatus.OK);
    }

    // 일정 일부 수정
    @PatchMapping("/{planId}")
    public ResponseEntity<PlanUpdateResponseDto> updatePlan(
            @PathVariable Long planId,
            @Valid @RequestBody PlanRequestDto dto
    ) {
        return new ResponseEntity<>(planService.updatePlan(planId, dto), HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(
            @PathVariable Long planId,
            @Valid @RequestBody PlanRequestDto dto
    ) {
         planService.deletePlan(planId, dto);

         return new ResponseEntity<>(HttpStatus.OK);
    }
}
