package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DoctorService")
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return doctorRepository.findByEmail(doctor.getEmail());
    }
}
