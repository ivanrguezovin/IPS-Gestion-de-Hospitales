package es.uniovi.ips.hospital.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor extends Staff {

    @ManyToMany(mappedBy = "doctors", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Diagnostic> diagnostics;

    public Doctor() {
        super();
    }

    public Doctor(String dni, String name, String surname, String email, String password, String addressStreet,
                  String addressCity, String addressZIPCode) {
        super(dni, name, surname, email, password, addressStreet, addressCity, addressZIPCode);
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Diagnostic> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(List<Diagnostic> diagnostics) {
        this.diagnostics = diagnostics;
    }

    @Override
    public String guiToString() {
        return getId() + " - Dr. " + getName() + " " + getSurname();
    }
}
