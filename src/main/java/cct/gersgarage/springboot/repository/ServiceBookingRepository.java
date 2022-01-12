package cct.gersgarage.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cct.gersgarage.springboot.model.ServiceBooking;

@Repository
public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Long>{

}
