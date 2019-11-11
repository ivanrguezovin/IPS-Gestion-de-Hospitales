package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.exception.BusinessException;
import es.uniovi.ips.hospital.repository.AppointmentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AppointmentService")
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment appointment) throws BusinessException {
    	if (appointmentRepository.findByPatientAndStartTime(appointment.getPatient(), appointment.getStartTime()) != null)
    		throw new BusinessException("This appointment already exists");
        appointmentRepository.save(appointment);
        return appointmentRepository.findByPatientAndStartTime(appointment.getPatient(), appointment.getStartTime());
    }
    
    public List<Appointment> findAllByPatient(Patient patient){
    	return appointmentRepository.findAllByPatient(patient);
    }
}
