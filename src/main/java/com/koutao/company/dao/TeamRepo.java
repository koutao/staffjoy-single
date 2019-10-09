package com.koutao.company.dao;

import com.koutao.company.bean.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team, String> {
    List<Team> findByCompanyId(String companyId);
}
