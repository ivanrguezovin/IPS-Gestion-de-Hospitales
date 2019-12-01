package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.MedicalRecord;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.repository.MedicalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MedicalRecordService")
public class MedicalRecordService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.save(medicalRecord);
		log.info("a medical record for the patient " + medicalRecord.getPatient().getEmail() + " has been created");
        return medicalRecordRepository.findByPatientAndDateAndDescriptionAndPrescription(medicalRecord.getPatient(),
				medicalRecord.getDate(),
                medicalRecord.getDescription(),
				medicalRecord.getPrescription());
    }

    public MedicalRecord findByPatient(Patient patient) {
    	log.info("the medical record of the patient " + patient.getEmail() + " has been retrieved");
        return medicalRecordRepository.findByPatient(patient);
    }

    public List<MedicalRecord> findAllByPatient(Patient patient) {
		log.info("the list of medical records of the patient " + patient.getEmail() + " has been retrieved");
        return medicalRecordRepository.findAllByPatient(patient);
    }

}
