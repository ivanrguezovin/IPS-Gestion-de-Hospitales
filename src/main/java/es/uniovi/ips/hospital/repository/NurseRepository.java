package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Nurse;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    // Add custom queries here
    Nurse findByEmail(String email);

	@Query("SELECT d FROM Nurse d JOIN d.schedules s WHERE s.startTime < ?1 and s.endTime > ?1")
    List<Nurse> findAvailableNurses(LocalDateTime appointment);
}
