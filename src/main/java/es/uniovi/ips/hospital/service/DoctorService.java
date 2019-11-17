package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Doctor updateDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return doctorRepository.findByEmail(doctor.getEmail());
    }

    public Optional<Doctor> findById(String string) {
        return doctorRepository.findById(Long.parseLong(string));
    }

    public Doctor findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }


    public List<Doctor> findAvailableDoctors(LocalDateTime appointmentDateTime) {
        return doctorRepository.findAvailableDoctors(appointmentDateTime);
    }
}
