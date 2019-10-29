package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Schedule;
import es.uniovi.ips.hospital.domain.Staff;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // Add custom queries here
    Schedule findByStartTimeAndEmployee(LocalDateTime startTime, Staff employee);
}
