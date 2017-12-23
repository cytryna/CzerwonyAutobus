package com.diligentia.CzerwonyAutobus.repository;

import com.diligentia.CzerwonyAutobus.model.SystemTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<SystemTO, Long> {

}
