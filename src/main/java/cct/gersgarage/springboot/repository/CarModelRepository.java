package cct.gersgarage.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cct.gersgarage.springboot.model.CarModel;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel,Long>{

}
