package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Patient;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Add custom queries here
    Patient findByEmail(String email);
	Patient findByDni(String dni);
	Optional<Patient> findById(Long id);
}
