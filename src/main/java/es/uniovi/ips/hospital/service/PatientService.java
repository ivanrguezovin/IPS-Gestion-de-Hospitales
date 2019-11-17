package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PatientService")
public class PatientService {

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
        return patientRepository.findByDni(patient.getDni());
    }
}
