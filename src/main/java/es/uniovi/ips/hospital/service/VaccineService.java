package es.uniovi.ips.hospital.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;
import es.uniovi.ips.hospital.domain.Vaccine.VaccineType;
import es.uniovi.ips.hospital.repository.VaccineRepository;

@Service("VaccineService")
public class VaccineService {
	@Autowired
	private VaccineRepository vaccineRepository;
	
	public List<Vaccine> findAllVaccines(){
		return vaccineRepository.findAll();
	}
	
	public List<Vaccine> findByPatient(Patient p) {
		return vaccineRepository.findByPatient(p);
	}
	
	public Vaccine findByVaccineType(VaccineType p) {
		return vaccineRepository.findByVaccineType(p);
	}
	
	public Vaccine findByDate(LocalDateTime p) {
		return vaccineRepository.findByDate(p);
	}
	
	public Optional<Vaccine> findById(Long p) {
		return vaccineRepository.findById(p);
	}
	
	public Optional<Vaccine> createVaccine(Vaccine v) {
		vaccineRepository.save(v);
		return vaccineRepository.findById(v.getId());
	}
	
}
