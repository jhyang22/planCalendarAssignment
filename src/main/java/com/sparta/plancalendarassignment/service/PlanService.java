package com.sparta.plancalendarassignment.service;

import com.sparta.plancalendarassignment.dto.PlanCreateResponseDto;
import com.sparta.plancalendarassignment.dto.PlanRequestDto;
import com.sparta.plancalendarassignment.dto.PlanResponseDto;
import com.sparta.plancalendarassignment.dto.PlanUpdateResponseDto;

import java.util.List;

public interface PlanService {

    PlanCreateResponseDto savePlan(PlanRequestDto dto);

    List<PlanResponseDto> findAllPlans();

    PlanResponseDto findPlanById(Long planId);

    PlanUpdateResponseDto updatePlan(Long planId, PlanRequestDto dto);

    void deletePlan(Long planId);
}
