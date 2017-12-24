package com.diligentia.CzerwonyAutobus.repository;

import com.diligentia.CzerwonyAutobus.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<Expense, Long> {

}
