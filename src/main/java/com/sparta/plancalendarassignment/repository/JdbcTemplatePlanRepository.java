package com.sparta.plancalendarassignment.repository;

import com.sparta.plancalendarassignment.dto.PlanCreateResponseDto;
import com.sparta.plancalendarassignment.dto.PlanResponseDto;
import com.sparta.plancalendarassignment.entity.Plan;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcTemplatePlanRepository implements PlanRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePlanRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public PlanCreateResponseDto savePlan(Plan plan) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("plans").usingGeneratedKeyColumns("plan_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", plan.getUserId());
        parameters.put("password", plan.getPassword());
        parameters.put("title", plan.getTitle());
        parameters.put("contents", plan.getContents());
        parameters.put("created_at", LocalDateTime.now());
        parameters.put("updated_at", LocalDateTime.now());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new PlanCreateResponseDto(key.longValue(), plan.getTitle(), plan.getContents(), plan.getCreatedAt());
    }

    @Override
    public List<PlanResponseDto> findAllPlans() {
        return jdbcTemplate.query("SELECT * FROM plans ORDER BY updated_at desc", planRowMapper());
    }

    @Override
    public int updatePlan(Long planId, String userId, String contents, LocalDateTime updatedAt) {
        return jdbcTemplate.update("UPDATE plans SET user_id = ?, contents = ?, updated_at = ? WHERE plan_id = ?", userId, contents, updatedAt, planId);
    }

    @Override
    public int deletePlan(Long planId) {
        return jdbcTemplate.update("DELETE FROM plans WHERE plan_id = ?", planId);
    }

    @Override
    public Plan findPlanByIdOrElseThrow(Long planId) {
        List<Plan> planList = jdbcTemplate.query("SELECT * FROM plans WHERE plan_id = ?", planRowMapperV2(), planId);

        return planList.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private RowMapper<PlanResponseDto> planRowMapper() {
        return new RowMapper<PlanResponseDto>() {

            @Override
            public PlanResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlanResponseDto(
                        rs.getLong("plan_id"),
                        rs.getString("user_id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<Plan> planRowMapperV2() {
        return new RowMapper<Plan>() {
            @Override
            public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Plan(
                        rs.getLong("plan_id"),
                        rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }
}
