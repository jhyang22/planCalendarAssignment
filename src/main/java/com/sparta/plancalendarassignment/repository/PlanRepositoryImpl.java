package com.sparta.plancalendarassignment.repository;

import com.sparta.plancalendarassignment.dto.PlanResponseDto;
import com.sparta.plancalendarassignment.entity.Plan;
import org.springframework.stereotype.Repository;
import java.util.*;
//
//@Repository
//public class PlanRepositoryImpl implements PlanRepository{
//    private final Map<Long, Plan> planList = new HashMap<>();
//
//    @Override
//    public Plan savePlan(Plan plan) {
//        Long planId = planList.isEmpty() ? 1 : Collections.max(planList.keySet()) + 1;
//        plan.setPlanId(planId);
//
//        planList.put(planId, plan);
//
//        return plan;
//    }
//
//    @Override
//    public List<PlanResponseDto> findAllPlans() {
//
//        List<PlanResponseDto> allPlans = new ArrayList<>();
//
//        for(Plan plan : planList.values()) {
//            PlanResponseDto responseDto = new PlanResponseDto((plan));
//            allPlans.add(responseDto);
//        }
//
//        return allPlans;
//    }
//
//    @Override
//    public Plan findPlanById(Long planId) {
//        return planList.get(planId);
//    }
//
//    @Override
//    public void deletePlan(Long planId) {
//        planList.remove(planId);
//    }
//}
