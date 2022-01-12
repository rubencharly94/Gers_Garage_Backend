package cct.gersgarage.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cct.gersgarage.springboot.model.Mechanic;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long>{

}
