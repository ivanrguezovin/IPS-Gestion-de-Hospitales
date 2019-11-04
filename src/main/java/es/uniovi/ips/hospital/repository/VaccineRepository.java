package es.uniovi.ips.hospital.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long>{
	Vaccine findByName(String name);
	Vaccine findByDate(LocalDateTime date);
	Vaccine findByPatient(Patient patient);
}
