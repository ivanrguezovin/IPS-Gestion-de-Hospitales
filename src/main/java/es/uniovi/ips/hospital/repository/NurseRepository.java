package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    // Add custom queries here
    Nurse findByEmail(String email);
}
