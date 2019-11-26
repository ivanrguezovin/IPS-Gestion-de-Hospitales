package es.uniovi.ips.hospital.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nurses")
public class Nurse extends Staff {

    @Column(name = "speciality")
    @NotEmpty
    private String speciality;

    @Column(unique = true, name = "collegeNumber")
    @NotNull
    private Long collegeNumber;
    
    @ManyToMany(mappedBy = "nurses", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

    public Nurse() {
        super();
    }

    public Nurse(String dni, String name, String surname, String email, String password, String addressStreet,
                 String addressCity, String addressZIPCode, String speciality, Long collegeNumber) {
        super(dni, name, surname, email, password, addressStreet, addressCity, addressZIPCode);
        this.speciality = speciality;
        this.collegeNumber = collegeNumber;
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
    
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
    
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
