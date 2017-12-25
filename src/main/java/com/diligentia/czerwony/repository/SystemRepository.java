package com.diligentia.czerwony.repository;

import com.diligentia.czerwony.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<Expense, Long> {

}
