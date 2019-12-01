package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Diagnostic;
import es.uniovi.ips.hospital.repository.DiagnosticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DiagnosticService")
public class DiagnosticService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiagnosticRepository diagnosticRepository;

    public List<Diagnostic> findAllByAppointment(Appointment appointment) {
        log.info("the diagnostic list of the patient " + appointment.getPatient().getEmail() + " has been retrieved");
        return diagnosticRepository.findAllByAppointment(appointment);
    }

    public Diagnostic createDiagnostic(Diagnostic diagnostic) {
        log.info("the diagnostic for the patient" + diagnostic.getAppointment().getPatient().getEmail() + " has been created");
        return diagnosticRepository.save(diagnostic);
    }
}
