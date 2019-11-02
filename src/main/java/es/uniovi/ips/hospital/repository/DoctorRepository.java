package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Doctor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
    Doctor findByEmail(String email);
    
    @Query("SELECT d FROM Doctor d JOIN d.schedules s WHERE s.startTime < ?1 and s.endTime > ?1")
    List<Doctor> findAvailableDoctors(LocalDateTime appointment);
}
