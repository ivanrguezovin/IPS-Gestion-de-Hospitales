package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAssitantRepository extends JpaRepository<AdminAssistant, Long> {
    // Add custom queries here
    AdminAssistant findByEmail(String email);
}
