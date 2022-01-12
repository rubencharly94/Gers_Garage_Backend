package cct.gersgarage.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cct.gersgarage.springboot.model.RepairType;

@Repository
public interface RepairTypeRepository extends JpaRepository<RepairType, Long>{

}
