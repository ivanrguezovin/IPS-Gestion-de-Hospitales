package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.repository.NurseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("NurseService")
public class NurseService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NurseRepository nurseRepository;

    public List<Nurse> findAllNurses() {
        return nurseRepository.findAll();
    }

    public Nurse createNurse(Nurse nurse) {
        nurseRepository.save(nurse);
        log.info("the nurse with the email " + nurse.getEmail() + " has been created");
        return nurseRepository.findByEmail(nurse.getEmail());
    }

    public Optional<Nurse> findById(String string) {
        return nurseRepository.findById(Long.parseLong(string));
    }

    public List<Nurse> findAvailableNurses(LocalDateTime appointmentDateTime) {
        return nurseRepository.findAvailableNurses(appointmentDateTime);
    }

    public Nurse updateNurse(Nurse nurse) {
        nurseRepository.save(nurse);
        log.info("the nurse with the email " + nurse.getEmail() + " has been updated");
        return nurseRepository.findByEmail(nurse.getEmail());
    }
}
