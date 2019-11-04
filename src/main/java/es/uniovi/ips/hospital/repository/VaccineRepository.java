package es.uniovi.ips.hospital.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long>{
	Vaccine findByName(String name);
	Vaccine findByDate(LocalDateTime date);
	List<Vaccine> findByPatient(Patient patient);
}
