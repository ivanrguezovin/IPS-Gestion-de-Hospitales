package es.uniovi.ips.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.ips.hospital.domain.MedicalRecord;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.repository.MedicalRecordRepository;

@Service("MedicalRecordService")
public class MedicalRecordService {
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.save(medicalRecord);
        return medicalRecordRepository.findByPatientAndDateAndDescriptionAndPrescription(medicalRecord.getPatient(), medicalRecord.getDate(),
        			medicalRecord.getDescription(), medicalRecord.getPrescription());
    }
    
    public MedicalRecord findByPatient(Patient patient) {
		return medicalRecordRepository.findByPatient(patient);
	}

}
