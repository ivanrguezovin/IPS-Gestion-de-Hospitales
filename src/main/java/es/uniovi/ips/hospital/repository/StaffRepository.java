package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByEmail(String email);
}
