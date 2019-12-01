package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.exception.BusinessException;
import es.uniovi.ips.hospital.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AppointmentService")
public class AppointmentService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment appointment) throws BusinessException {
        if (appointmentRepository.findByPatientAndStartTime(appointment.getPatient(), appointment.getStartTime()) != null)
            throw new BusinessException("This appointment already exists");
        appointmentRepository.save(appointment);
        log.info("the appointment for the patient " + appointment.getPatient() + " has been created");
        return appointmentRepository.findByPatientAndStartTime(appointment.getPatient(), appointment.getStartTime());
    }

    public List<Appointment> findAllByDoctor(Doctor myself) {
        log.info("the appointment list of the doctor " + myself.getEmail() + " has been retrieved");
        return appointmentRepository.findAllByDoctorsAndConfirmed(myself, true);
    }


    public List<Appointment> findAllByPatient(Patient patient) {
        log.info("the appointment list of the patient " + patient.getEmail() + " has been retrieved");
        return appointmentRepository.findAllByPatientAndConfirmed(patient, true);
    }

    public void updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> findAllByNurse(Nurse myself) {
        log.info("the appointment list of the nurse " + myself.getEmail() + " has been retrieved");
        return appointmentRepository.findAllByNursesAndConfirmed(myself, true);
    }

    public void removeAppointment(Appointment appointment) {
        log.info("the appointment for the patient " + appointment.getPatient().getEmail() + " has been removed");
        appointmentRepository.delete(appointment);
    }
}
