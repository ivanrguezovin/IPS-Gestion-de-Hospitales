package es.uniovi.ips.hospital.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;
import es.uniovi.ips.hospital.domain.Vaccine.VaccineType;

public interface VaccineRepository extends JpaRepository<Vaccine, Long>{
	Vaccine findByVaccineType(VaccineType vt);
	Vaccine findByDate(LocalDateTime date);
	List<Vaccine> findByPatient(Patient patient);
	Optional<Vaccine> findById(Long id);
}
