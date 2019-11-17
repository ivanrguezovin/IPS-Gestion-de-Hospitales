package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {

    List<Diagnostic> findAllByAppointment(Appointment appointment);
}
