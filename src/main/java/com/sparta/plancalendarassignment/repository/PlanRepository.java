package com.sparta.plancalendarassignment.repository;

import com.sparta.plancalendarassignment.dto.PlanResponseDto;
import com.sparta.plancalendarassignment.entity.Plan;

import java.util.List;

public interface PlanRepository {
    Plan savePlan(Plan plan);

    List<PlanResponseDto> findAllPlans();

    Plan findPlanById(Long planId);

    void deletePlan(Long planId);
}
