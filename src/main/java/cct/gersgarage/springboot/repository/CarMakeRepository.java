package cct.gersgarage.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cct.gersgarage.springboot.model.CarMake;

@Repository
public interface CarMakeRepository extends JpaRepository<CarMake,Long>{

}
