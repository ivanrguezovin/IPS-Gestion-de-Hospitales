package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NurseService")
public class NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    public List<Nurse> findAllNurses() {
        return nurseRepository.findAll();
    }

    public Nurse createNurse(Nurse nurse) {
        nurseRepository.save(nurse);
        return nurseRepository.findByEmail(nurse.getEmail());
    }
}
