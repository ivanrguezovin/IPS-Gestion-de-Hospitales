package es.uniovi.ips.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor extends Staff {

    @ManyToMany(mappedBy = "doctors", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

    @NotEmpty
    @Column(name = "speciality")
    private String speciality;

    @NotNull
    @Column(unique = true, name = "collegeNumber")
    private Long collegeNumber;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Diagnostic> diagnostics;

    public Doctor() {
        super();
    }

    public Doctor(String dni, String name, String surname, String email, String password, String addressStreet,
                  String addressCity, String addressZIPCode, String speciality, Long collegeNumber) {
        super(dni, name, surname, email, password, addressStreet, addressCity, addressZIPCode);
        this.speciality = speciality;
        this.collegeNumber = collegeNumber;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Long getCollegeNumber() {
        return collegeNumber;
    }

    public void setCollegeNumber(Long collegeNumber) {
        this.collegeNumber = collegeNumber;
    }

    public List<Diagnostic> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(List<Diagnostic> diagnostics) {
        this.diagnostics = diagnostics;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }


    @Override
    public String guiToString() {
        return getId() + " - Dr. " + getName() + " " + getSurname();
    }
}
