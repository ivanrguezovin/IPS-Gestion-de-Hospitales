package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("PatientService")
public class PatientService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }

    public Patient findPatientByDni(String dni) {
        return patientRepository.findByDni(dni);
    }


    public Patient findPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public Patient createPatient(Patient patient) {
        patientRepository.save(patient);
        log.info("the patient with the email " + patient.getEmail() + " has been created");
        return patientRepository.findByDni(patient.getDni());
    }

    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }
}
