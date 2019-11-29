package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Diagnostic;
import es.uniovi.ips.hospital.repository.DiagnosticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DiagnosticService")
public class DiagnosticService {

    @Autowired
    private DiagnosticRepository diagnosticRepository;

    public List<Diagnostic> findAllByAppointment(Appointment appointment) {
        return diagnosticRepository.findAllByAppointment(appointment);
    }

    public Diagnostic createDiagnostic(Diagnostic diagnostic) {
        return diagnosticRepository.save(diagnostic);
    }
}
