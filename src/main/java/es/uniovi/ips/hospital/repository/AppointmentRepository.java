package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByPatientAndStartTime(Patient patient, LocalDateTime startTime);

    List<Appointment> findAllByDoctors(Doctor doctor);

    List<Appointment> findAllByPatient(Patient patient);
}
