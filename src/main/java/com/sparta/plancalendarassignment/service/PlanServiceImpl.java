package com.sparta.plancalendarassignment.service;

import com.sparta.plancalendarassignment.dto.PlanCreateResponseDto;
import com.sparta.plancalendarassignment.dto.PlanRequestDto;
import com.sparta.plancalendarassignment.dto.PlanResponseDto;
import com.sparta.plancalendarassignment.dto.PlanUpdateResponseDto;
import com.sparta.plancalendarassignment.entity.Plan;
import com.sparta.plancalendarassignment.repository.PlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public PlanCreateResponseDto savePlan(PlanRequestDto dto) {
        Plan plan = new Plan(dto.getTitle(), dto.getContents(), dto.getUserId(), dto.getPassword());

        Plan savedPlan = planRepository.savePlan(plan);

        return new PlanCreateResponseDto(savedPlan);
    }

    @Override
    public List<PlanResponseDto> findAllPlans() {

        return planRepository.findAllPlans();
    }

    @Override
    public PlanResponseDto findPlanById(Long planId) {

        Plan plan = planRepository.findPlanById(planId);

        if (plan == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new PlanResponseDto(plan);
    }

    @Override
    public PlanUpdateResponseDto updatePlan(Long planId, PlanRequestDto dto) {

        Plan plan = planRepository.findPlanById(planId);

        if (plan == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (dto.getTitle() == null || dto.getContents() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (dto.getPassword().equals(plan.getPassword())) {
            plan.updatePlan(dto);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return new PlanUpdateResponseDto(plan);
    }

    @Override
    public void deletePlan(Long planId) {
        planRepository.deletePlan(planId);
    }

}
