package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Add custom queries here
    Doctor findByEmail(String email);
}
