package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Schedule;
import es.uniovi.ips.hospital.domain.Staff;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	
    Schedule findByStartTimeAndEmployee(LocalDateTime startTime, Staff employee);
    
    @Query("SELECT s FROM Schedule s WHERE s.startTime < ?1 and s.endTime > ?1 and s.employee = ?2")
	Schedule findMatchingSchedule(LocalDateTime time, Staff employee);
}
