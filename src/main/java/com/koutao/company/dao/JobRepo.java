package com.koutao.company.dao;

import com.koutao.company.bean.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job, String> {
    List<Job> findJobByTeamId(String teamId);
    Job findJobById(String id);
}
