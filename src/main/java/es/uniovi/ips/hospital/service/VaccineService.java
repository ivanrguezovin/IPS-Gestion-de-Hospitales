package es.uniovi.ips.hospital.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;
import es.uniovi.ips.hospital.repository.VaccineRepository;

@Service("VaccineService")
public class VaccineService {
	@Autowired
	private VaccineRepository vaccineRepository;
	
	public List<Vaccine> findAllPatients(){
		return vaccineRepository.findAll();
	}
	
	public Vaccine findByPatient(Patient p) {
		return vaccineRepository.findByPatient(p);
	}
	
	public Vaccine findByName(String p) {
		return vaccineRepository.findByName(p);
	}
	
	public Vaccine findByDate(LocalDateTime p) {
		return vaccineRepository.findByDate(p);
	}
	
}
