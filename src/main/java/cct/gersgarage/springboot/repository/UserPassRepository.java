package cct.gersgarage.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cct.gersgarage.springboot.model.UserPass;
@Repository
public interface UserPassRepository extends JpaRepository<UserPass,Long>{

}
