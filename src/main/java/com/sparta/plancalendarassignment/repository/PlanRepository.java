package com.sparta.plancalendarassignment.repository;

import com.sparta.plancalendarassignment.dto.PlanCreateResponseDto;
import com.sparta.plancalendarassignment.dto.PlanResponseDto;
import com.sparta.plancalendarassignment.entity.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanRepository {
    PlanCreateResponseDto savePlan(Plan plan);

    List<PlanResponseDto> findAllPlans();

    Optional<Plan> findPlanById(Long planId);

    void deletePlan(Long planId);
}
