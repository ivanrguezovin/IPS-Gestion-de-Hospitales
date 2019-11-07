package es.uniovi.ips.hospital.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctors")
@Transactional
public class Doctor extends Staff {

    @ManyToMany(mappedBy = "doctors", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

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

    @Override
    public String guiToString() {
        return getId() + " - Dr. " + getName() + " " + getSurname();
    }
}
