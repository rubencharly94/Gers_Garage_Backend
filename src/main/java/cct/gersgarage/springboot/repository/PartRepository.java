package cct.gersgarage.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cct.gersgarage.springboot.model.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long>{

}
