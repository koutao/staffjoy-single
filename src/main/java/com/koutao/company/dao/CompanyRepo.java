package com.koutao.company.dao;

import com.koutao.company.bean.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, String> {
    Company findCompanyById(String id);
}
