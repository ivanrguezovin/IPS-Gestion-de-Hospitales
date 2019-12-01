package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("DoctorService")
public class DoctorService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }


    public Doctor createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        log.info("the doctor with the email " + doctor.getEmail() + " has been created");
        return doctorRepository.findByEmail(doctor.getEmail());
    }

    public Doctor updateDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        log.info("the doctor with the email " + doctor.getEmail() + " has been updated");
        return doctorRepository.findByEmail(doctor.getEmail());
    }

    public Optional<Doctor> findById(String string) {
        log.info("the doctor with the ID " + string + " has been retrieved");
        return doctorRepository.findById(Long.parseLong(string));
    }

    public Doctor findByEmail(String email) {
        log.info("the doctor with the email " + email + " has been retrieved");
        return doctorRepository.findByEmail(email);
    }

    public List<Doctor> findAvailableDoctors(LocalDateTime appointmentDateTime) {
        return doctorRepository.findAvailableDoctors(appointmentDateTime);
    }
}
