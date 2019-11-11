package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Patient;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Appointment findByPatientAndStartTime(Patient patient, LocalDateTime startTime);
	List<Appointment> findAllByPatient(Patient patient);
}
