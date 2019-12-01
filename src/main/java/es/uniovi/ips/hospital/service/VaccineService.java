package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;
import es.uniovi.ips.hospital.domain.Vaccine.VaccineType;
import es.uniovi.ips.hospital.repository.VaccineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("VaccineService")
public class VaccineService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VaccineRepository vaccineRepository;

    public List<Vaccine> findAllVaccines() {
        return vaccineRepository.findAll();
    }

    public List<Vaccine> findByPatient(Patient p) {
        log.info("list vaccines" + p.getEmail());
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


    public void createVaccine(Vaccine v) {
        vaccineRepository.save(v);
        log.info("the vaccine " + v.getDescription() + " has been created");
    }

    public void removeVaccine(Vaccine v) {
        vaccineRepository.delete(v);
        log.info("the vaccine " + v.getDescription() + " has been removed");
    }
}
