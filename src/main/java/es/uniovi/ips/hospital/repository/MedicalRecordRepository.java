package es.uniovi.ips.hospital.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.ips.hospital.domain.MedicalRecord;
import es.uniovi.ips.hospital.domain.Patient;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

	MedicalRecord findByPatientAndDateAndDescriptionAndPrescription(Patient patient, LocalDateTime date, String description, String prescription);
	MedicalRecord findByPatient(Patient patient);
	List<MedicalRecord> findAllByPatient(Patient patient);
	
}
