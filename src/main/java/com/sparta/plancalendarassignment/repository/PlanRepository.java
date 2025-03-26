package com.sparta.plancalendarassignment.repository;

import com.sparta.plancalendarassignment.dto.PlanCreateResponseDto;
import com.sparta.plancalendarassignment.dto.PlanResponseDto;
import com.sparta.plancalendarassignment.entity.Plan;

import java.time.LocalDateTime;
import java.util.List;

public interface PlanRepository {
    PlanCreateResponseDto savePlan(Plan plan);

    List<PlanResponseDto> findAllPlans(String userId, LocalDateTime updatedAt);

    int updatePlan(Long planId, String userId, String contents, LocalDateTime updatedAt);

    int deletePlan(Long planId);

    Plan findPlanByIdOrElseThrow(Long planId);
}
