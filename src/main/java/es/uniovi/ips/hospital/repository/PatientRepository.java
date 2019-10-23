package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Add custom queries here
    Patient findByEmail(String email);
}
