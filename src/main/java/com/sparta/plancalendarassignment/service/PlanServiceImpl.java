package com.sparta.plancalendarassignment.service;

import com.sparta.plancalendarassignment.dto.PlanCreateResponseDto;
import com.sparta.plancalendarassignment.dto.PlanRequestDto;
import com.sparta.plancalendarassignment.dto.PlanResponseDto;
import com.sparta.plancalendarassignment.dto.PlanUpdateResponseDto;
import com.sparta.plancalendarassignment.entity.Plan;
import com.sparta.plancalendarassignment.repository.PlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

        return planRepository.savePlan(plan);
    }

    @Override
    public List<PlanResponseDto> findAllPlans() {

        return planRepository.findAllPlans();
    }

    @Override
    public PlanResponseDto findPlanById(Long planId) {

        Plan plan = planRepository.findPlanByIdOrElseThrow(planId);

        return new PlanResponseDto(plan);
    }

    @Transactional
    @Override
    public PlanUpdateResponseDto updatePlan(Long planId, PlanRequestDto dto) {

        if (dto.getUserId() == null || dto.getContents() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int updateRow = planRepository.updatePlan(planId, dto.getUserId(), dto.getContents(), LocalDateTime.now());

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        //null이거나 비었으면 => orElseThrow()하겠다~
        Plan plan = planRepository.findPlanByIdOrElseThrow(planId);

        if (dto.getPassword().equals(plan.getPassword())) {
            return new PlanUpdateResponseDto(plan);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deletePlan(Long planId, PlanRequestDto dto) {

        Plan plan = planRepository.findPlanByIdOrElseThrow(planId);

        if (dto.getPassword().equals(plan.getPassword())) {
            int deletedRow = planRepository.deletePlan(planId);

            if (deletedRow == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }


}
